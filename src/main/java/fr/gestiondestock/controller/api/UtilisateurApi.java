package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.UtilisateurDto;
import fr.gestiondestock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

@Api(value = APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateurs/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un utilisateur (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier un utilisateur" , response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet utilisateur n'est pas valide")
    })
    UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{utilisateurId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur par son ID" , notes = "Cette methode permet de chercher un utilisateur par son ID" , response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucun utilisateur n'existe dans la BDD avec l'ID fourni")
    })
    UtilisateurDto findById(@PathVariable("utilisateurId") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des fournisseurs" , notes = "Cette methode permet de chercher et renvoyer la liste des fournisseurs qui existent dans la BDD" , responseContainer = "List<UtilisateurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs / Une liste vide"),
    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{utilisateurId}")
    @ApiOperation(value = "Supprimer un utilisateur par son ID" , notes = "Cette methode permet de supprimer un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a ete supprime"),
    })
    void deleteById(@PathVariable("utilisateurId") Integer id);

}
