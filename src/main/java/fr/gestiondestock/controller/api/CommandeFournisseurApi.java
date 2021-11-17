package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

@Api(value = APP_ROOT + "/commandeFournisseurs")
public interface CommandeFournisseurApi {

    @PostMapping(value = APP_ROOT + "/commandeFournisseurs/create" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande fournisseur (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier une commande fournisseur" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet commande fournisseur n'est pas valide")
    })
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value = APP_ROOT + "/commandeFournisseurs/{idCommandeFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par son ID" , notes = "Cette methode permet de chercher une commande fournisseur par son ID" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune commande fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/commandeFournisseurs/{codeCommandeFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par son CODE" , notes = "Cette methode permet de chercher une commande fournisseur par son CODE" , response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune commande fournisseur n'existe dans la BDD avec le CODE fourni")
    })
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(value = APP_ROOT + "/commandeFournisseurs/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des commandes fournisseur" , notes = "Cette methode permet de chercher et renvoyer la liste des  commande fournisseurs qui existent dans la BDD" , responseContainer = "List<CommandeFournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes fournisseur / Une liste vide"),
    })
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandeFournisseurs/delete/{idCommandeFournisseur}")
    @ApiOperation(value = "Supprimer une commande fournisseur par son ID" , notes = "Cette methode permet de supprimer une commande fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete supprime"),
    })
    void deleteById(@PathVariable("idCommandeFournisseur") Integer id);

}
