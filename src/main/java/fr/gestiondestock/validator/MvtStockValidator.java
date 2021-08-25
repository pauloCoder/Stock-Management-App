package fr.gestiondestock.validator;

import fr.gestiondestock.dto.MvtStockDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvtStockValidator {

    public static List<String> validate(MvtStockDto mvtStockDto) {

        List<String> errors = new ArrayList<>();

        if (mvtStockDto == null) {
            errors.add("Veuillez saisir la quantité d'article du mouvement");
            errors.add("Veuillez saisir la date du mouvement");
            errors.add("Veuillez saisir le type de mouvement");
            errors.add("Veuillez saisir l'article concernée par le mouvement");
            return errors;
        }

        if (mvtStockDto.getQuantite() == null || mvtStockDto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
            errors.add("Veuillez saisir la quantité d'article du mouvement");
        }

        if (mvtStockDto.getDateMvt() == null) {
            errors.add("Veuillez saisir la date du mouvement");
        }

        if (mvtStockDto.getTypeMvt() == null) {
            errors.add("Veuillez saisir le type de mouvement");
        }

        if (mvtStockDto.getArticle() == null) {
            errors.add("Veuillez saisir l'article concernée par le mouvement");
        }

        return errors;

    }

}
