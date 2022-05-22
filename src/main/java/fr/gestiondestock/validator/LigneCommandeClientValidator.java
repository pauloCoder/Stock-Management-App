package fr.gestiondestock.validator;

import fr.gestiondestock.dto.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto) {

        List<String> errors = new ArrayList<>();

        if (ligneCommandeClientDto == null) {
            errors.add("Veuillez saisir la quantité d'article de la ligne de commande client");
            errors.add("Veuillez saisir le prix unitaire de la ligne de la commande client");
            errors.add("Veuillez saisir l'article de la ligne de la commande client");
            errors.add("Veuillez saisir la commande liée à la ligne de la commande client");
            errors.addAll(ArticleValidator.validate(null));
            return errors;
        }

        if (ligneCommandeClientDto.getQuantite() == null) {
            errors.add("Veuillez saisir la quantité d'article de la ligne de la commande client");
        }

        if (ligneCommandeClientDto.getPrixUnitaire() == null) {
            errors.add("Veuillez saisir le prix unitaire de la ligne de la commande client");
        }

        if (ligneCommandeClientDto.getArticle() == null) {
            errors.addAll(ArticleValidator.validate(null));
        }

        return errors;

    }

}
