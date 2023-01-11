package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.ArticleApi;
import fr.gestiondestock.dto.ArticleDto;
import fr.gestiondestock.dto.LigneCommandeClientDto;
import fr.gestiondestock.dto.LigneCommandeFournisseurDto;
import fr.gestiondestock.dto.LigneVenteDto;
import fr.gestiondestock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<ArticleDto> save(ArticleDto articleDto) {
        return ResponseEntity.ok(articleService.save(articleDto));
    }

    @Override
    public ResponseEntity<ArticleDto> findById(Integer id) {

        return ResponseEntity.ok(articleService.findById(id));
    }

    @Override
    public ResponseEntity<ArticleDto> findByCodeArticle(String codeArticle) {
        return ResponseEntity.ok(articleService.findByCodeArticle(codeArticle));
    }

    @Override
    public ResponseEntity<List<LigneVenteDto>> findHistoriqueVentes(Integer idArticle) {
        return ResponseEntity.ok(articleService.findHistoriqueVentes(idArticle));
    }

    @Override
    public ResponseEntity<List<LigneCommandeClientDto>> findHistoriqueCommandeClient(Integer idArticle) {
        return ResponseEntity.ok(articleService.findHistoriqueCommandeClient(idArticle));
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return ResponseEntity.ok(articleService.findHistoriqueCommandeFournisseur(idArticle));
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAllArticleByIdCategory(Integer idCategory) {
        return ResponseEntity.ok(articleService.findAllArticleByIdCategory(idCategory));
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        articleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
