package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.ArticleDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.Article;
import fr.gestiondestock.repository.ArticleRepository;
import fr.gestiondestock.services.ArticleService;
import fr.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()) {

            log.error("Article is not valid {}",articleDto);
            throw new EntityNotValidException( "L'article n'est pas valide" , ErrorCodes.ARTICLE_NOT_VALID , errors);
        }

        Article savedArticle = articleRepository.save(ArticleDto.toEntity(articleDto));
        return ArticleDto.fromEntity(savedArticle);

    }

    @Override
    public ArticleDto findById(Integer id) {

        if (id == null) {
            log.error("Article id is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);
        return ArticleDto.fromEntity(
                article.orElseThrow( () -> {
                    log.error("Inexistant article for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucun article avec l'ID %s n'a été trouve dans la BDD",id) ,ErrorCodes.ARTICLE_NOT_FOUND);
                } )
        );

    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (StringUtils.hasLength(codeArticle)) {
            log.error("Article CODE is null");
            return null;
        }

        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);
        return ArticleDto.fromEntity(
                article.orElseThrow( () -> {
                    log.error("Inexistant article for CODE {}",codeArticle);
                    throw new EntityNotFoundException(String.format("Aucun article avec le CODE %s n'a été trouvé dans la BDD",codeArticle) ,ErrorCodes.ARTICLE_NOT_FOUND);
                } )
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                                .map(ArticleDto::fromEntity)
                                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Article ID is null");
            return;
        }

        articleRepository.deleteById(id);

    }

}
