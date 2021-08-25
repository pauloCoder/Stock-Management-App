package fr.gestiondestock.validator;

import fr.gestiondestock.dto.LigneCommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {

    public static List<String> validate(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {

        List<String> errors = new ArrayList<>();

        if (ligneCommandeFournisseurDto == null) {
            errors.add("Veuillez saisir la quantité d'article de la ligne de la commande fournisseur");
            errors.add("Veuillez saisir le prix unitaire de la ligne de la commande fournisseur");
            errors.add("Veuillez saisir l'article de la ligne de la commande fournisseur");
            errors.add("Veuillez saisir la commande liée à la ligne de la commande fournisseur");
            return errors;
        }

        if (ligneCommandeFournisseurDto.getQuantite() == null) {
            errors.add("Veuillez saisir la quantité d'article de la ligne de la commande fournisseur");
        }

        if (ligneCommandeFournisseurDto.getPrixUnitaire() == null) {
            errors.add("Veuillez saisir le prix unitaire de la ligne de la commande fournisseur");
        }

        if (ligneCommandeFournisseurDto.getArticle() == null) {
            errors.add("Veuillez saisir l'article de la ligne de la commande fournisseur");
        }

        if (ligneCommandeFournisseurDto.getCommandeFournisseur() == null) {
            errors.add("Veuillez saisir la commande liée à la ligne de la commande fournisseur");
        }

        return errors;

    }
}
