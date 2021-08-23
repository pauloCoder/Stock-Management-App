package fr.gestiondestock.validator;

import fr.gestiondestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(final UtilisateurDto utilisateurDto) {

        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null) {

        }

        if (StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez saisir le nom d'utilisateur");
        }

        return errors;

    }

}
