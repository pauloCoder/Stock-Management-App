package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.ArticleApi;
import fr.gestiondestock.dto.ArticleDto;
import fr.gestiondestock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

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
    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        articleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
