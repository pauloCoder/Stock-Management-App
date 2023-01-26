package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.*;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.exception.InvalidOperationException;
import fr.gestiondestock.model.*;
import fr.gestiondestock.repository.ArticleRepository;
import fr.gestiondestock.repository.CommandeFournisseurRepository;
import fr.gestiondestock.repository.FournisseurRepository;
import fr.gestiondestock.repository.LigneCommandeFournisseurRepository;
import fr.gestiondestock.services.CommandeFournisseurService;
import fr.gestiondestock.services.MvtStockService;
import fr.gestiondestock.validator.ArticleValidator;
import fr.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {


    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;
    private final ArticleRepository articleRepository;
    private final MvtStockService mvtStockService;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, FournisseurRepository fournisseurRepository, ArticleRepository articleRepository, MvtStockService mvtStockService) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.mvtStockService = mvtStockService;
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
                    log.error("Inexistant commande fournisseur for id {}" ,id);
                    throw new EntityNotFoundException(String.format("Aucune commande fournisseur avec l'ID %s n'a pas ete trouvee en BDD", id), ErrorCodes.FOURNISSEUR_NOT_FOUND);
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
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(id);
        if (!ligneCommandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une commande fournisseur contenant des lignes de commande fournisseur", ErrorCodes.COMMANDE_FOURNISSEUR_ALREADY_IN_USE);
        }
        commandeFournisseurRepository.deleteById(id);

    }

    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);
        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("Etat commande fournisseur is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        commandeFournisseurDto.setEtatCommande(etatCommande);
        CommandeFournisseur savedCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));
        if (commandeFournisseurDto.isCommandeLivree()) {
            updateMvtStock(idCommande);
        }
        return CommandeFournisseurDto.fromEntity(savedCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        LigneCommandeFournisseur ligneCommandeFournisseur = findLigneCommandeFournisseur(idLigneCommande);
        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOperationException("Impossible de modifier la quantite d'une ligne de commande avec une quantite null ou negative",ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
        ligneCommandeFournisseur.setQuantite(quantite);
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        checkIdCommande(idCommande);
        if (idFournisseur == null) {
            log.error("Fournisseur ID is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null",ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
        Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur)
                .orElseThrow(() ->  {
                    log.error("Inexistant fournisseur for id {}",idFournisseur);
                    throw new EntityNotFoundException(String.format("Aucun fournisseur avec l'ID %s n'a ete trouvee dans la BDD",idFournisseur) ,ErrorCodes.FOURNISSEUR_NOT_FOUND);
                });
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        commandeFournisseurDto.setFournisseur(FournisseurDto.fromEntity(fournisseur));
        CommandeFournisseur savedCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));
        return CommandeFournisseurDto.fromEntity(savedCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle, "nouvel");
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        LigneCommandeFournisseur ligneCommandeFournisseur = findLigneCommandeFournisseur(idLigneCommande);
        Article article = articleRepository.findById(idArticle)
                .orElseThrow(() -> {
                    log.error("Inexistant article for id {}",idArticle);
                    throw new EntityNotFoundException(String.format("Aucun article avec l'ID %s n'a été trouve dans la BDD",idArticle) ,ErrorCodes.ARTICLE_NOT_FOUND);
                });
        List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(article));
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}",article);
            throw new EntityNotValidException( "L'article n'est pas valide" , ErrorCodes.ARTICLE_NOT_VALID , errors);
        }

        ligneCommandeFournisseur.setArticle(article);
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        // Verification de l'existence de la ligne de commande fournisseur
        findLigneCommandeFournisseur(idLigneCommande);
        ligneCommandeFournisseurRepository.deleteById(idLigneCommande);
        return commandeFournisseurDto;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByIdCommandeFournisseur(Integer idCommande) {
        return ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande)
                .stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    private void checkIdCommande(Integer idCommande) {
        if (idCommande == null) {
            log.error("Commande client ID is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande un ID commande null",ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (idLigneCommande == null) {
            log.error("Ligne commande fournisseur ID is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID ligne commande null",ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
    }

    private void checkIdArticle(Integer idArticle, String message) {
        if (idArticle == null) {
            log.error(String.format("%s article ID is null", Objects.equals(message, "ancien") ? "Old" : "New"));
            throw new InvalidOperationException(String.format("Impossible de modifier l'etat de la commande avec un %s ID article null",message),ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
    }

    private CommandeFournisseurDto checkEtatCommande(Integer idCommande) {
        CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);
        if (commandeFournisseurDto.isCommandeLivree()) {
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
        return commandeFournisseurDto;
    }

    private LigneCommandeFournisseur findLigneCommandeFournisseur(Integer idLigneCommande) {
        return ligneCommandeFournisseurRepository.findById(idLigneCommande)
                .orElseThrow(() ->  {
                    log.error("Inexistant commande client for id {}",idLigneCommande);
                    throw new EntityNotFoundException(String.format("Aucune commande fournisseur avec l'ID %s n'a ete trouvee dans la BDD",idLigneCommande) ,ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
                });
    }

    private void updateMvtStock(Integer idCommande) {
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande);
        ligneCommandeFournisseurs.forEach(ligneCommandeFournisseur -> {
            MvtStockDto entreeStock = MvtStockDto.builder()
                    .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                    .quantite(ligneCommandeFournisseur.getQuantite())
                    .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                    .dateMvt(Instant.now())
                    .typeMvt(TypeMvtStock.ENTREE)
                    .sourceMvt(SourceMvtStock.COMMANDE_FOURNISSEUR)
                    .build();
            mvtStockService.sortieStockArticle(entreeStock);
        });
    }
}
