package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.UtilisateurDto;
import fr.gestiondestock.dto.VentesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

@Api(value = APP_ROOT + "/ventes")
public interface VentesApi {

    @GetMapping(value = APP_ROOT + "/ventes/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un vente (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier un vente" , response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet vente n'est pas valide")
    })
    VentesDto save(@RequestBody VentesDto ventesDto);

    @GetMapping(value = APP_ROOT + "/ventes/{idVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par son ID" , notes = "Cette methode permet de chercher une vente par son ID" , response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune vente n'existe dans la BDD avec l'ID fourni")
    })
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/{codeVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par son CODE" , notes = "Cette methode permet de chercher une vente par son CODE" , response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune vente n'existe dans la BDD avec le CODE fourni")
    })
    VentesDto findByCode(@PathVariable("codeVente") String code);

    @GetMapping(value = APP_ROOT + "/ventes/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des ventes" , notes = "Cette methode permet de chercher et renvoyer la liste des ventes qui existent dans la BDD" , responseContainer = "List<VentesDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ventes / Une liste vide"),
    })
    List<VentesDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/ventes/{idVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer un vente par son ID" , notes = "Cette methode permet de supprimer un vente par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a ete supprime"),
    })
    void deleteById(@PathVariable("idVente") Integer id);

}
