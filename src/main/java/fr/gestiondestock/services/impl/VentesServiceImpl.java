package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.ArticleDto;
import fr.gestiondestock.dto.LigneVenteDto;
import fr.gestiondestock.dto.MvtStockDto;
import fr.gestiondestock.dto.VentesDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.exception.InvalidOperationException;
import fr.gestiondestock.model.*;
import fr.gestiondestock.repository.ArticleRepository;
import fr.gestiondestock.repository.LigneVenteRepository;
import fr.gestiondestock.repository.VentesRepository;
import fr.gestiondestock.services.MvtStockService;
import fr.gestiondestock.services.VentesService;
import fr.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private final VentesRepository ventesRepository;
    private final LigneVenteRepository ligneVenteRepository;
    private final ArticleRepository articleRepository;
    private final MvtStockService mvtStockService;

    @Autowired
    public VentesServiceImpl(VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository, ArticleRepository articleRepository, MvtStockService mvtStockService) {
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.articleRepository = articleRepository;
        this.mvtStockService = mvtStockService;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {

        List<String> errors = VentesValidator.validate(ventesDto);
        if (!errors.isEmpty()) {
            log.error("Ventes is not valid : {}", ventesDto);
            throw new EntityNotValidException("La vente n'est pas valide", ErrorCodes.VENTES_NOT_VALID,errors);
        }

        List<String> articleErrors = new ArrayList<>();
        if (ventesDto.getLigneVentes() != null) {
            ventesDto.getLigneVentes().forEach( ligneVenteDto -> {
                if (ligneVenteDto.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
                    if (article.isEmpty()) {
                        log.warn("Article with ID {} was not found in DB", ligneVenteDto.getArticle().getId());
                        articleErrors.add(String.format("L'article avec l'ID %s n'existe pas", ligneVenteDto.getArticle().getId()));
                    }
                    else {
                        articleErrors.add("Impossible d'enregistrer une vente avec un article null");
                    }
                }
            });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("Articles inexistant in DB");
            throw new EntityNotValidException("Article inexistant en BDD", ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(ventesDto));

        if (ventesDto.getLigneVentes() != null) {
            ventesDto.getLigneVentes().forEach(ligneVenteDto -> {
                LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
                ligneVente.setVentes(savedVentes);
                ligneVenteRepository.save(ligneVente);
                updateMvtStock(ligneVente);
            });

        }

        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {

        if (id == null) {
            log.error("Ventes ID is null");
            return null;
        }

        Optional<Ventes> ventes = ventesRepository.findById(id);

        return VentesDto.fromEntity(
                ventes.orElseThrow(()->{
                    log.error("Inexistant Ventes for ID {}", id);
                    throw new EntityNotFoundException(String.format("Aucune vente avec l'ID %s n'a ete trouve en BDD", id), ErrorCodes.VENTES_NOT_VALID);
                })
        );
    }

    @Override
    public VentesDto findByCode(String code) {

        if (code == null) {
            log.error("Ventes CODE is null");
            return null;
        }

        Optional<Ventes> ventes = ventesRepository.findVentesByCodeVente(code);

        return VentesDto.fromEntity(
                ventes.orElseThrow(()->{
                    log.error("Inexistant Ventes for CODe {}", code);
                    throw new EntityNotFoundException(String.format("Aucune vente avec le CODE %s n'a ete trouve en BDD", code), ErrorCodes.VENTES_NOT_VALID);
                })
        );

    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll()
                               .stream()
                               .map(VentesDto::fromEntity)
                               .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            log.error("Ventes ID is null");
            return;
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVentesId(id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une vente contenant des lignes de vente", ErrorCodes.VENTES_ALREADY_IN_USE);
        }
        ventesRepository.deleteById(id);

    }

    private void updateMvtStock(LigneVente ligneVente) {
        MvtStockDto sortieStock = MvtStockDto.builder()
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .quantite(ligneVente.getQuantite())
                .idEntreprise(ligneVente.getIdEntreprise())
                .dateMvt(Instant.now())
                .typeMvt(TypeMvtStock.SORTIE)
                .sourceMvt(SourceMvtStock.VENTE)
                .build();
        mvtStockService.sortieStockArticle(sortieStock);
    }

}
