package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.UtilisateurDto;
import fr.gestiondestock.dto.VentesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static fr.gestiondestock.utils.Constants.VENTES_ENDPOINT;

@Api(value = VENTES_ENDPOINT)
public interface VentesApi {

    @GetMapping(value = VENTES_ENDPOINT + "/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un vente (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier un vente" , response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet vente n'est pas valide")
    })
    ResponseEntity<VentesDto> save(@RequestBody VentesDto ventesDto);

    @GetMapping(value = VENTES_ENDPOINT + "/{idVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par son ID" , notes = "Cette methode permet de chercher une vente par son ID" , response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune vente n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<VentesDto> findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = VENTES_ENDPOINT + "/{codeVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par son CODE" , notes = "Cette methode permet de chercher une vente par son CODE" , response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune vente n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<VentesDto> findByCode(@PathVariable("codeVente") String code);

    @GetMapping(value = VENTES_ENDPOINT + "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des ventes" , notes = "Cette methode permet de chercher et renvoyer la liste des ventes qui existent dans la BDD" , responseContainer = "List<VentesDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ventes / Une liste vide"),
    })
    ResponseEntity<List<VentesDto>> findAll();

    @DeleteMapping(value = VENTES_ENDPOINT + "/{idVente}")
    @ApiOperation(value = "Supprimer un vente par son ID" , notes = "Cette methode permet de supprimer un vente par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a ete supprime"),
    })
    ResponseEntity deleteById(@PathVariable("idVente") Integer id);

}
