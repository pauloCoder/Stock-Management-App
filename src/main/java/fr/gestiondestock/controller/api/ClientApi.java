package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.CLIENT_ENDPOINT;

@Api(value = CLIENT_ENDPOINT)
public interface ClientApi {

    @PostMapping(value = CLIENT_ENDPOINT + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client (Ajouter / Modifier)", notes = "Cette methode permet d'enregistrer ou modifier un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet client n'est pas valide")
    })
    ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto);

    @GetMapping(value = CLIENT_ENDPOINT + "/find/id/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client par son ID", notes = "Cette methode permet de chercher un client par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun client n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<ClientDto> findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = CLIENT_ENDPOINT + "/find/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des clients", notes = "Cette methode permet de chercher et renvoyer la liste des clients qui existent dans la BDD", responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des clients / Une liste vide"),
    })
    ResponseEntity<List<ClientDto>> findAll();

    @DeleteMapping(value = CLIENT_ENDPOINT + "/delete/id/{idClient}")
    @ApiOperation(value = "Supprimer un client par son ID", notes = "Cette methode permet de supprimer un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a ete supprime"),
    })
    ResponseEntity<Void> deleteById(@PathVariable("idClient") Integer id);

}
