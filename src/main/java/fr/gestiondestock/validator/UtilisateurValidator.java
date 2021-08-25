package fr.gestiondestock.validator;

import fr.gestiondestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(final UtilisateurDto utilisateurDto) {

        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null) {
            errors.add("Veuillez saisir le nom d'utilisateur");
            errors.add("Veuillez saisir le prenom d'utilisateur");
            errors.add("Veuillez saisir le mot de passe de l'utilisateur");
            errors.add("Veuillez saisir l'email de l'utilisateur");
            errors.add("Veuillez saisir la date de naissance de l'utilisateur");
            errors.addAll(AdresseValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez saisir le nom d'utilisateur");
        }

        if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez saisir le prenom d'utilisateur");
        }

        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
            errors.add("Veuillez saisir le mot de passe de l'utilisateur");
        }

        if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez saisir l'email de l'utilisateur");
        }

        if (utilisateurDto.getDateDeNaissance() == null) {
            errors.add("Veuillez saisir la date de naissance de l'utilisateur");
        }

        errors.addAll(AdresseValidator.validate(utilisateurDto.getAdresse()));

        return errors;

    }

}
