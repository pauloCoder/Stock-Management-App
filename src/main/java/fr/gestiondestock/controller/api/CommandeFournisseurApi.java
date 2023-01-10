package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.CommandeFournisseurDto;
import fr.gestiondestock.dto.LigneCommandeFournisseurDto;
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

import static fr.gestiondestock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

@Api(value = COMMANDE_FOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    @PostMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/create" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande fournisseur (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier une commande fournisseur" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet commande fournisseur n'est pas valide")
    })
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/commandeFournisseurs/update/etat/{idCommande}/{etatCommande}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modification de l'état d'une commande fournisseur" , notes = "Cette methode permet de modifier l'état d'une commande fournisseur" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'etat de la commande fournisseur a ete modifie"),
            @ApiResponse(code = 400 , message = "L'etat de la commande fournisseur ne peut etre modifie")
    })
    ResponseEntity<CommandeFournisseurDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/commandeFournisseurs/update/qantite/{idCommande}/{idLigneCommande}/{quantite}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modification de la quantite d'une ligne de commande fournisseur" , notes = "Cette methode permet de modifier la quantite d'une ligne de commande fournisseur" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La quantite de la ligne de commande a ete modifie"),
            @ApiResponse(code = 400 , message = "La quantite de la ligne de commande ne peut etre modifiee")
    })
    ResponseEntity<CommandeFournisseurDto> updateQuantiteCommandee(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/commandeFournisseurs/update/fournisseur/{idCommande}/{idFournisseur}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modification du fournisseur proprietaire d'une commande" , notes = "Cette methode permet de modifier le fournisseur associé à la commande fournisseur" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur associé a la commande fournisseur a ete modifié"),
            @ApiResponse(code = 400 , message = "Le fournisseur associé a la commande ne peut etre modifié")
    })
    ResponseEntity<CommandeFournisseurDto> updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);

    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/commandeFournisseurs/update/article/{idCommande}/{idLigneCommande}/{idArticle}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modification d'un article de la commande" , notes = "Cette methode permet de modifier un article dans une commande" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article associé a la commande fournisseur a ete modifié"),
            @ApiResponse(code = 400 , message = "L'article associé a la commande ne peut etre modifié")
    })
    ResponseEntity<CommandeFournisseurDto> updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/commandeFournisseurs/delete/article/{idCommande}/{idLigneCommande}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Suppression d'un article de la commande" , notes = "Cette methode permet de supprimer un article dans une commande" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article associé a la commande fournisseur a été supprimé"),
            @ApiResponse(code = 400 , message = "L'article associé a la commande ne peut etre supprimé")
    })
    ResponseEntity<CommandeFournisseurDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/commandeFournisseurs/find/lignesCommande/{idCommande}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recherche de toutes les lignes de commande d'une commande" , notes = "Cette methode permet de rechercher toutes les lignes de commande d'une commande avec un ID commande" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste de lignes de commande a été trouvé en BDD"),
            @ApiResponse(code = 400 , message = "Aucune ligne de commande fournisseur n'a été trouvé en BDD pour l'ID fourni")
    })
    ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLignesCommandesFournisseurByIdCommandeFournisseur(@PathVariable("idCommande") Integer idCommande);

    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par son ID" , notes = "Cette methode permet de chercher une commande fournisseur par son ID" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune commande fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<CommandeFournisseurDto> findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/{codeCommandeFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par son CODE" , notes = "Cette methode permet de chercher une commande fournisseur par son CODE" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune commande fournisseur n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des commandes fournisseur" , notes = "Cette methode permet de chercher et renvoyer la liste des  commande fournisseurs qui existent dans la BDD" , responseContainer = "List<CommandeFournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes fournisseur / Une liste vide"),
    })
    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    @DeleteMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}")
    @ApiOperation(value = "Supprimer une commande fournisseur par son ID" , notes = "Cette methode permet de supprimer une commande fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete supprime"),
    })
    ResponseEntity<Void> deleteById(@PathVariable("idCommandeFournisseur") Integer id);

}
