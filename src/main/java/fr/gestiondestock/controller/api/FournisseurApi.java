package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.FOURNISSEUR_ENDPOINT;

@Api(value = FOURNISSEUR_ENDPOINT)
public interface FournisseurApi {

    @PostMapping(value = FOURNISSEUR_ENDPOINT + "/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un fournisseur (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier un fournisseur" , response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet fournisseur n'est pas valide")
    })
    ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = FOURNISSEUR_ENDPOINT + "/{idFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un fournisseur par son ID" , notes = "Cette methode permet de chercher un fournisseur par son ID" , response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucun fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<FournisseurDto> findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = FOURNISSEUR_ENDPOINT + "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des fournisseurs" , notes = "Cette methode permet de chercher et renvoyer la liste des fournisseurs qui existent dans la BDD" , responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs / Une liste vide"),
    })
    ResponseEntity<List<FournisseurDto>> findAll();

    @DeleteMapping(value = FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
    @ApiOperation(value = "Supprimer un fournisseur par son ID" , notes = "Cette methode permet de supprimer un fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur a ete supprime"),
    })
    ResponseEntity deleteById(@PathVariable("idFournisseur") Integer id);
}
