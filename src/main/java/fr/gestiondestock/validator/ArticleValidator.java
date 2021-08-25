package fr.gestiondestock.validator;

import fr.gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(final ArticleDto articleDto) {

        List<String> errors = new ArrayList<>();

        if (articleDto == null) {
            errors.add("Veuillez saisir le code de l'article");
            errors.add("Veuillez saisir la désignation de l'article");
            errors.add("Veuillez saisir le prix unitaire HT de l'article");
            errors.add("Veuillez saisir le taux TVA de l'article");
            errors.add("Veuillez saisir le prix unitaire TTC de l'article");
            errors.add("Veuillez sélectionner une catégorie");
            return errors;
        }

        if (!StringUtils.hasLength(articleDto.getCodeArticle())) {
            errors.add("Veuillez saisir le code de l'article");
        }

        if (!StringUtils.hasLength(articleDto.getDesignation())) {
            errors.add("Veuillez saisir la désignation de l'article");
        }

        if (articleDto.getPrixUnitaireHt() == null) {
            errors.add("Veuillez saisir le prix unitaire HT de l'article");
        }

        if (articleDto.getTauxTva() == null) {
            errors.add("Veuillez saisir le taux TVA de l'article");
        }

        if (articleDto.getPrixUnitaireTtc() == null) {
            errors.add("Veuillez saisir le prix unitaire TTC de l'article");
        }

        if (articleDto.getCategory() == null) {
            errors.add("Veuillez sélectionner une catégorie");
        }
        return errors;
    }

}
