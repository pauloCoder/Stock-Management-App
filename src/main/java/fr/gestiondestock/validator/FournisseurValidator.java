package fr.gestiondestock.validator;

import fr.gestiondestock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(final FournisseurDto fournisseurDto) {

        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null) {
            errors.add("Veuillez saisir le nom du fournisseur");
            errors.add("Veuillez saisir le prenom du fournisseur");
            errors.add("Veuillez saisir l'email du fournisseur");
            errors.add("Veuillez saisir le téléphone du fournisseur");
            errors.addAll(AdresseValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(fournisseurDto.getNom())) {
            errors.add("Veuillez saisir le nom du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getPrenom())) {
            errors.add("Veuillez saisir le prenom du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getEmail())) {
            errors.add("Veuillez saisir l'email du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getTelephone())) {
            errors.add("Veuillez saisir le téléphone du fournisseur");
        }

        errors.addAll(AdresseValidator.validate(fournisseurDto.getAdresse()));

        return errors;

    }
}
