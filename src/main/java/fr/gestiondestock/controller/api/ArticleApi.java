package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.ArticleDto;
import fr.gestiondestock.dto.LigneCommandeClientDto;
import fr.gestiondestock.dto.LigneCommandeFournisseurDto;
import fr.gestiondestock.dto.LigneVenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.gestiondestock.utils.Constants.ARTICLE_ENDPOINT;

@Api(value = ARTICLE_ENDPOINT )
public interface ArticleApi {

    @PostMapping(value = ARTICLE_ENDPOINT + "/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article (Ajouter / Modifier)" , notes = "Cette methode permet d'enregistrer ou modifier un article" , response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article cree / modifie"),
            @ApiResponse(code = 400 , message = "L'objet article n'est pas valide")
    })
    ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = ARTICLE_ENDPOINT + "/{idArticle}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par ID" , notes = "Cette methode permet de chercher un article par son ID" , response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<ArticleDto> findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = ARTICLE_ENDPOINT + "/{codeArticle}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par CODE" , notes = "Cette methode permet de chercher un article par son CODE" , response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article a ete trouve dans la BDD"),
            @ApiResponse(code = 404 , message = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<ArticleDto> findByCodeArticle(@PathVariable("codeArticle") String codeArticle);


    @GetMapping(value = ARTICLE_ENDPOINT + "/historique/vente/{idArticle}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneVenteDto>> findHistoriqueVentes(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = ARTICLE_ENDPOINT + "/historique/commandeClient/{idArticle}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCommandeClientDto>> findHistoriqueCommandeClient(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = ARTICLE_ENDPOINT + "/historique/commandeFournisseur/{idArticle}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCommandeFournisseurDto>> findHistoriqueCommandeFournisseur( @PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = ARTICLE_ENDPOINT + "filter/category/{idCategory}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ArticleDto>> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory);

    @GetMapping(value = ARTICLE_ENDPOINT + "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des articles" , notes = "Cette methode permet de chercher et renvoyer la liste des articles qui existent dans la BDD" , responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des articles / Une liste vide"),
    })
    ResponseEntity<List<ArticleDto>> findAll();

    @DeleteMapping(value = ARTICLE_ENDPOINT + "/delete/{idArticle}")
    @ApiOperation(value = "Supprimer un article par son ID" , notes = "Cette methode permet de supprimer un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete supprime"),
    })
    ResponseEntity<Void> delete(@PathVariable("idArticle") Integer id);
}
