package fr.gestiondestock.validator;

import fr.gestiondestock.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {

    public static List<String> validate(LigneVenteDto ligneVenteDto) {

        List<String> errors = new ArrayList<>();

        if (ligneVenteDto == null) {
            errors.add("Veuillez saisir la quantité d'article de la ligne de vente");
            errors.add("Veuillez saisir le prix unitaire de la ligne de vente");
            errors.add("Veuillez saisir l'article de la ligne de vente");
            errors.add("Veuillez saisir la vente liée à la ligne de vente");
            return errors;
        }

        if (ligneVenteDto.getQuantite() == null) {
            errors.add("Veuillez saisir la quantité d'article de la ligne de vente");
        }

        if (ligneVenteDto.getPrixUnitaire() == null) {
            errors.add("Veuillez saisir le prix unitaire de la ligne de vente");
        }

        if (ligneVenteDto.getArticle() == null) {
            errors.add("Veuillez saisir l'article de la ligne de vente");
        }

        if (ligneVenteDto.getVente() == null) {
            errors.add("Veuillez saisir la vente liée à la ligne de vente");
        }

        return errors;

    }

}
