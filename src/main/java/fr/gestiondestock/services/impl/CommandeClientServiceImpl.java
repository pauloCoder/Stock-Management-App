package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.CommandeClientDto;
import fr.gestiondestock.dto.LigneCommandeClientDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.Article;
import fr.gestiondestock.model.Client;
import fr.gestiondestock.model.CommandeClient;
import fr.gestiondestock.model.LigneCommandeClient;
import fr.gestiondestock.repository.ArticleRepository;
import fr.gestiondestock.repository.ClientRepository;
import fr.gestiondestock.repository.CommandeClientRepository;
import fr.gestiondestock.repository.LigneCommandeClientRepository;
import fr.gestiondestock.services.CommandeClientService;
import fr.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,LigneCommandeClientRepository ligneCommandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository) {
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
            throw new EntityNotValidException("La commande client n'est pas valide" , ErrorCodes.COMMANDE_CLIENT_NOT_VALID , errors);
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
            throw new EntityNotValidException("Article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
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
}
