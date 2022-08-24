package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.CommandeFournisseurDto;
import fr.gestiondestock.dto.LigneCommandeFournisseurDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.*;
import fr.gestiondestock.repository.ArticleRepository;
import fr.gestiondestock.repository.CommandeFournisseurRepository;
import fr.gestiondestock.repository.FournisseurRepository;
import fr.gestiondestock.repository.LigneCommandeFournisseurRepository;
import fr.gestiondestock.services.CommandeFournisseurService;
import fr.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {


    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, FournisseurRepository fournisseurRepository, ArticleRepository articleRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {

        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);

        if (!errors.isEmpty()) {
            log.error("Commande fournisseur is not valid : {}",commandeFournisseurDto);
            throw new EntityNotValidException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} not found in DB",commandeFournisseurDto.getFournisseur().getId());
            throw new EntityNotFoundException(String.format("Aucun fournisseur avec l'ID %s n'a ete trouve en BDD",commandeFournisseurDto.getFournisseur().getId()),ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null) {
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach( ligneCommandeFournisseurDto -> {
                if (ligneCommandeFournisseurDto.getArticle() != null) {

                    Optional<Article> article = articleRepository.findById(ligneCommandeFournisseurDto.getArticle().getId());
                    if (article.isEmpty()) {
                        log.warn("Article with ID {} was not found in DB",ligneCommandeFournisseurDto.getArticle().getId());
                        articleErrors.add(String.format("L'article avec l'ID %s n'existe pas",ligneCommandeFournisseurDto.getArticle().getId()));
                    }
                    else {
                        articleErrors.add("Impossible d'enregistrer une commande avec un article null");
                    }
                }
            });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("Articles inexistant in DB");
            throw new EntityNotValidException("Article inexistant en BDD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
        CommandeFournisseur savedCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null) {
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligneCommandeFournisseurDto -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCommandeFournisseur);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });

        }

        return CommandeFournisseurDto.fromEntity(savedCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {

        if (id == null) {
            log.error("Commande fournisseur ID is null");
            return null;
        }

        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findById(id);

        return CommandeFournisseurDto.fromEntity(
                commandeFournisseur.orElseThrow(()-> {
                    log.error("Inexistant commande fournisseur for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucune commande fournisseur avec l'ID %s n'a pas ete trouvee en BDD",id),ErrorCodes.FOURNISSEUR_NOT_FOUND);
                })
        );

    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (code == null) {
            log.error("Commande fournisseur CODE is null");
            return null;
        }

        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findCommandeFournisseurByCodeCommande(code);

        return CommandeFournisseurDto.fromEntity(
                commandeFournisseur.orElseThrow(()-> {
                    log.error("Inexistant commande fournisseur for code {}",code);
                    throw new EntityNotFoundException(String.format("Aucune commande fournisseur avec le CODE %s n'a pas ete trouvee en BDD",code),ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
                })
        );
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll()
                                            .stream()
                                            .map(CommandeFournisseurDto::fromEntity)
                                            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            log.error("Commande fournisseur ID is null");
            return;
        }

        commandeFournisseurRepository.deleteById(id);

    }

    @Override
    public LigneCommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return null;
    }

    @Override
    public LigneCommandeFournisseurDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return null;
    }

    @Override
    public LigneCommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return null;
    }

    @Override
    public LigneCommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle) {
        return null;
    }

    @Override
    public LigneCommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return null;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLignesCommandesClientByIdCommandeClient(Integer idCommande) {
        return null;
    }
}
