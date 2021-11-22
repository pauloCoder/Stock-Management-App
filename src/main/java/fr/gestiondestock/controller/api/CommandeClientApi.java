package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity deleteById(@PathVariable("idCommandeClient") Integer id);

}
