package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.CommandeClientDto;
import fr.gestiondestock.dto.LigneCommandeClientDto;
import fr.gestiondestock.model.EtatCommande;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static fr.gestiondestock.utils.Constants.COMMANDE_CLIENT_ENDPOINT;

@Api(value = COMMANDE_CLIENT_ENDPOINT)
public interface CommandeClientApi {

    @PostMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande client (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier une commande client" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);

    @PatchMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/update/etat/{idCommande}/{etatCommande}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modification de l'état d'une commande client" , notes = "Cette methode permet de modifier l'état d'une commande client" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'etat de la commande client a ete modifie"),
            @ApiResponse(code = 400 , message = "L'etat de la commande client ne peut etre modifie")
    })
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/update/qantite/{idCommande}/{idLigneCommande}/{quantite}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modification de la quantite d'une ligne de commande client" , notes = "Cette methode permet de modifier la quantite d'une ligne de commande client" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La quantite de la ligne de commande a ete modifie"),
            @ApiResponse(code = 400 , message = "La quantite de la ligne de commande ne peut etre modifiee")
    })
    ResponseEntity<CommandeClientDto> updateQuantiteCommandee(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite")BigDecimal quantite);

    @PatchMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/update/client/{idCommande}/{idClient}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modification du client proprietaire d'une commande" , notes = "Cette methode permet de modifier le client associé à une commande client" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client associé a la commande client a ete modifié"),
            @ApiResponse(code = 400 , message = "Le client associé a la commande ne peut etre modifié")
    })
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient") Integer idClient);

    @PatchMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/update/article/{idCommande}/{idLigneCommande}/{idArticle}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modification d'un article de la commande" , notes = "Cette methode permet de modifier un article dans une commande" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article associé a la commande client a ete modifié"),
            @ApiResponse(code = 400 , message = "L'article associé a la commande ne peut etre modifié")
    })
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/delete/article/{idCommande}/{idLigneCommande}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Suppression d'un article de la commande" , notes = "Cette methode permet de supprimer un article dans une commande" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article associé a la commande client a été supprimé"),
            @ApiResponse(code = 400 , message = "L'article associé a la commande ne peut etre supprimé")
    })
    ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/find/lignesCommande/{idCommande}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recherche de toutes les lignes de commandes d'une commande" , notes = "Cette methode permet de rechercher toutes les lignes de commandes d'une commande avec un ID commande" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste de lignes de commande a été trouvé en BDD"),
            @ApiResponse(code = 400 , message = "Aucune ligne de commande client n'a été trouvé en BDD pour l'ID fourni")
    })
    ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByIdCommandeClient(@PathVariable("idCommande") Integer idCommande);

    @GetMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/{idClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande client par son ID" , notes = "Cette methode permet de chercher une commande client par son ID" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune commande client n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/{codeClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande client par son CODE" , notes = "Cette methode permet de chercher une commande client par son CODE" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune commande client n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeClient") String code);

    @GetMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des commandes client" , notes = "Cette methode permet de chercher et renvoyer la liste des  commande clients qui existent dans la BDD" , responseContainer = "List<CommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes client / Une liste vide"),
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = COMMANDE_CLIENT_ENDPOINT + "/commandeClients/delete/{idCommandeClient}")
    @ApiOperation(value = "Supprimer une commande client par son ID" , notes = "Cette methode permet de supprimer une commande client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete supprime"),
    })
    ResponseEntity<Void> deleteById(@PathVariable("idCommandeClient") Integer id);

}
