package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import fr.gestiondestock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;

@Api(value = UTILISATEUR_ENDPOINT)
public interface UtilisateurApi {

    @PostMapping(value = UTILISATEUR_ENDPOINT + "/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un utilisateur (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier un utilisateur" , response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet utilisateur n'est pas valide")
    })
    ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = UTILISATEUR_ENDPOINT + "/{utilisateurId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur par son ID" , notes = "Cette methode permet de chercher un utilisateur par son ID" , response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucun utilisateur n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<UtilisateurDto> findById(@PathVariable("utilisateurId") Integer id);

    @GetMapping(value = UTILISATEUR_ENDPOINT + "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des fournisseurs" , notes = "Cette methode permet de chercher et renvoyer la liste des fournisseurs qui existent dans la BDD" , responseContainer = "List<UtilisateurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs / Une liste vide"),
    })
    ResponseEntity<List<UtilisateurDto>> findAll();

    @DeleteMapping(value = UTILISATEUR_ENDPOINT + "/delete/{utilisateurId}")
    @ApiOperation(value = "Supprimer un utilisateur par son ID" , notes = "Cette methode permet de supprimer un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a ete supprime"),
    })
    ResponseEntity<Void> deleteById(@PathVariable("utilisateurId") Integer id);

    @PostMapping(value = UTILISATEUR_ENDPOINT + "/change/password" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier le mot de passe d'un utilisateur" , notes = "Cette methode permet de modifier le mot de passe d'un utilisateur" , response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet utilisateur n'est pas valide")
    })
    ResponseEntity<UtilisateurDto> changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto);

    // Ajouter la modification du mot de passe

}
