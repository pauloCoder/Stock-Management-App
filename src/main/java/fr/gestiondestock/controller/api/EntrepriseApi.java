package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

@Api(value = APP_ROOT + "/entreprises")
public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "/entreprises/create" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une entreprise (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier une entreprise" , response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet entreprise cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet entreprise n'est pas valide")
    })
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = APP_ROOT + "/entreprises/{entrepriseId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une entreprise par son ID" , notes = "Cette methode permet de chercher une entreprise par son ID" , response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet entreprise a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune entreprise n'existe dans la BDD avec l'ID fourni")
    })
    EntrepriseDto findById(@PathVariable("entrepriseId") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprises/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des entreprises" , notes = "Cette methode permet de chercher et renvoyer la liste des entreprises qui existent dans la BDD" , responseContainer = "List<EntrepriseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des entreprisess / Une liste vide"),
    })
    List<EntrepriseDto> findAll();

    @GetMapping(value = APP_ROOT + "/entreprises/{entrepriseId}")
    @ApiOperation(value = "Supprimer une entreprise par son ID" , notes = "Cette methode permet de supprimer une entreprise par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a ete supprime")
    })
    void deleteById(@PathVariable("entrepriseId") Integer id);

}
