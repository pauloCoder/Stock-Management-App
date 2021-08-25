package fr.gestiondestock.validator;

import fr.gestiondestock.dto.AdresseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {

    public static List<String> validate(final AdresseDto adresseDto) {

        List<String> errors = new ArrayList<>();

        if (adresseDto == null) {
            errors.add("Veuillez saisir le champ adresse 1");
            errors.add("Veuillez saisir votre ville");
            errors.add("Veuillez saisir votre code postal");
            errors.add("Veuillez saisir votre pays");
            return errors;
        }

        if (!StringUtils.hasLength(adresseDto.getAdresse1())) {
            errors.add("Veuillez saisir le champ adresse 1");
        }

        if (!StringUtils.hasLength(adresseDto.getVille())) {
            errors.add("Veuillez saisir votre ville");
        }

        if (!StringUtils.hasLength(adresseDto.getCodePostal())) {
            errors.add("Veuillez saisir votre code postal");
        }

        if (!StringUtils.hasLength(adresseDto.getPays())) {
            errors.add("Veuillez saisir votre pays");
        }

        return errors;

    }
}
