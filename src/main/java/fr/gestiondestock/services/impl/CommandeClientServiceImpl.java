package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.ArticleDto;
import fr.gestiondestock.dto.ClientDto;
import fr.gestiondestock.dto.CommandeClientDto;
import fr.gestiondestock.dto.LigneCommandeClientDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.exception.InvalidOperationException;
import fr.gestiondestock.model.*;
import fr.gestiondestock.repository.ArticleRepository;
import fr.gestiondestock.repository.ClientRepository;
import fr.gestiondestock.repository.CommandeClientRepository;
import fr.gestiondestock.repository.LigneCommandeClientRepository;
import fr.gestiondestock.services.CommandeClientService;
import fr.gestiondestock.validator.ArticleValidator;
import fr.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private final CommandeClientRepository commandeClientRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, LigneCommandeClientRepository ligneCommandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()) {
            log.error("Commande client is not valid {}",commandeClientDto);
            throw new EntityNotValidException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID , errors);
        }

        if (commandeClientDto.getId() != null && commandeClientDto.isCommandeLivree()) {
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        Optional<Client> client =  clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client with ID {} was not found in the DB",commandeClientDto.getClient().getId());
            throw new EntityNotFoundException(String.format("Aucun client avec l'ID %s n'a ete trouve dans la BDD",commandeClientDto.getClient().getId()) ,ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();
        if (commandeClientDto.getLigneCommandeClients() != null) {
            commandeClientDto.getLigneCommandeClients().forEach(ligneCommandeClientDto -> {
                if (ligneCommandeClientDto.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligneCommandeClientDto.getArticle().getId());
                    if (article.isEmpty()) {
                        log.warn("Article with ID {} was not found in the DB", ligneCommandeClientDto.getArticle().getId());
                        articleErrors.add(String.format("L'article avec l'ID %s n'existe pas",ligneCommandeClientDto.getArticle().getId()));
                    }
                }
                else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article null");
                }
            });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("Articles inexistant in DB");
            throw new EntityNotValidException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient savedCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if (commandeClientDto.getLigneCommandeClients() != null) {
            commandeClientDto.getLigneCommandeClients().forEach(ligneCommandeClientDto -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCommandeClientDto);
                ligneCommandeClient.setCommandeClient(savedCommandeClient);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedCommandeClient);

    }

    @Override
    public CommandeClientDto findById(Integer id) {

        if (id == null) {
            log.error("Commande client ID is null");
            return null;
        }

        Optional<CommandeClient> commandeClient = commandeClientRepository.findById(id);
        return CommandeClientDto.fromEntity(
                commandeClient.orElseThrow( () -> {
                    log.error("Inexistant commandeClient for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucune commande client avec l'ID %s n'a ete trouvee dans la BDD",id) ,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
                } )
        );

    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (StringUtils.hasLength(code)) {
            log.error("Commande client code is null");
            return null;
        }

        Optional<CommandeClient> commandeClient = commandeClientRepository.findCommandeClientByCodeCommande(code);
        return CommandeClientDto.fromEntity(
                commandeClient.orElseThrow( () -> {
                    log.error("Inexistant commandeClient for code {}",code);
                    throw new EntityNotFoundException(String.format("Aucune commande client avec le CODE %s n'a été trouvé dans la BDD",code) ,ErrorCodes.CATEGORY_NOT_FOUND);
                } )
        );
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll()
                .stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            log.error("CommandeClient ID is null");
            return;
        }

        commandeClientRepository.deleteById(id);

    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {

        checkIdCommande(idCommande);

        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("Etat commande client is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        commandeClientDto.setEtatCommande(etatCommande);
        CommandeClient savedCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        return CommandeClientDto.fromEntity(savedCommandeClient);
    }

    @Override
    public CommandeClientDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        LigneCommandeClient ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOperationException("Impossible de modifier la quantite d'une ligne de commande avec une quantite null ou negative",ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        ligneCommandeClient.setQuantite(quantite);
        ligneCommandeClientRepository.save(ligneCommandeClient);
        return commandeClientDto;
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {

        checkIdCommande(idCommande);

        if (idClient == null) {
            log.error("Client ID is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null",ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() ->  {
                    log.error("Inexistant client for id {}",idClient);
                    throw new EntityNotFoundException(String.format("Aucun client avec l'ID %s n'a ete trouvee dans la BDD",idClient) ,ErrorCodes.CLIENT_NOT_FOUND);
                });

        commandeClientDto.setClient(ClientDto.fromEntity(client));
        CommandeClient savedCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        return CommandeClientDto.fromEntity(savedCommandeClient);
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle, "nouvel");

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        LigneCommandeClient ligneCommandeClient = findLigneCommandeClient(idLigneCommande);
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

        ligneCommandeClient.setArticle(article);
        ligneCommandeClientRepository.save(ligneCommandeClient);
        return commandeClientDto;
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        // Verification de l'existence de la ligne de commande client
        findLigneCommandeClient(idLigneCommande);
        ligneCommandeClientRepository.deleteById(idLigneCommande);
        return commandeClientDto;
    }

    @Override
    public List<LigneCommandeClientDto> findAllLignesCommandesClientByIdCommandeClient(Integer idCommande) {
        return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande)
                .stream()
                .map(LigneCommandeClientDto::fromEntity)
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
            log.error("Ligne commande client ID is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID ligne commande null",ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private void checkIdArticle(Integer idArticle, String message) {
        if (idArticle == null) {
            log.error(String.format("%s article ID is null", Objects.equals(message, "ancien") ? "Old" : "New"));
            throw new InvalidOperationException(String.format("Impossible de modifier l'etat de la commande avec un %s ID article null",message),ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private CommandeClientDto checkEtatCommande(Integer idCommande) {
        CommandeClientDto commandeClientDto = findById(idCommande);
        if (commandeClientDto.isCommandeLivree()) {
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
        return commandeClientDto;
    }

    private LigneCommandeClient findLigneCommandeClient(Integer idLigneCommande) {
        return ligneCommandeClientRepository.findById(idLigneCommande)
                .orElseThrow(() ->  {
                    log.error("Inexistant commande client for id {}",idLigneCommande);
                    throw new EntityNotFoundException(String.format("Aucune commande client avec l'ID %s n'a ete trouvee dans la BDD",idLigneCommande) ,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
                });
    }
}
