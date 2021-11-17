package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

@Api(value = APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier une categorie" , response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet categorie n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par son ID" , notes = "Cette methode permet de chercher une categorie par son ID" , response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par son CODE" , notes = "Cette methode permet de chercher une categorie par son CODE" , response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucune categorie n'existe dans la BDD avec le CODE fourni")
    })
    CategoryDto findByCode(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/categories/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des categories" , notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existent dans la BDD" , responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide"),
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer une categorie par son ID" , notes = "Cette methode permet de supprimer une categorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete supprime"),
    })
    void deleteById(@PathVariable("idCategory") Integer id);

}
