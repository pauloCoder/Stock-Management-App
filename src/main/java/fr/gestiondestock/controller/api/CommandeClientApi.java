package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.ClientDto;
import fr.gestiondestock.dto.CommandeClientDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandeClients/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande client (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier une commande client" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet commande client n'est pas valide")
    })
    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(value = APP_ROOT + "/commandeClients/{idClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande client par son ID" , notes = "Cette methode permet de chercher une commande client par son ID" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune commande client n'existe dans la BDD avec l'ID fourni")
    })
    CommandeClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/commandeClients/{codeClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande client par son CODE" , notes = "Cette methode permet de chercher une commande client par son CODE" , response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune commande client n'existe dans la BDD avec le CODE fourni")
    })
    CommandeClientDto findByCode(@PathVariable("codeClient") String code);

    @GetMapping(value = APP_ROOT + "/commandeClients/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des commandes client" , notes = "Cette methode permet de chercher et renvoyer la liste des  commande clients qui existent dans la BDD" , responseContainer = "List<CommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes client / Une liste vide"),
    })
    List<CommandeClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandeClients/delete/{idCommandeClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer une commande client par son ID" , notes = "Cette methode permet de supprimer une commande client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete supprime"),
    })
    void deleteById(@PathVariable("idCommandeClient") Integer id);

}
