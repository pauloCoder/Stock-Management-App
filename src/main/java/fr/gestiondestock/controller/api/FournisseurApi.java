package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.ClientDto;
import fr.gestiondestock.dto.FournisseurDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseurs/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un fournisseur (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier un fournisseur" , response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet fournisseur n'est pas valide")
    })
    FournisseurDto save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un fournisseur par son ID" , notes = "Cette methode permet de chercher un fournisseur par son ID" , response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucun fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des fournisseurs" , notes = "Cette methode permet de chercher et renvoyer la liste des fournisseurs qui existent dans la BDD" , responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs / Une liste vide"),
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}")
    @ApiOperation(value = "Supprimer un fournisseur par son ID" , notes = "Cette methode permet de supprimer un fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur a ete supprime"),
    })
    void deleteById(@PathVariable("idFournisseur") Integer id);
}
