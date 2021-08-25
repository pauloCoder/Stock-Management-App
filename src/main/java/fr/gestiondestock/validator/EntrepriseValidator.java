package fr.gestiondestock.validator;

import fr.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto) {

        List<String> errors = new ArrayList<>();

        if (entrepriseDto == null) {
            errors.add("Veuillez saisir le nom de l'entreprise");
            errors.add("Veuillez saisir la description de l'entreprise");
            errors.add("Veuillez saisir le code fiscal de l'entreprise");
            errors.add("Veuillez saisir l'email de l'entreprise");
            errors.add("Veuillez saisir le numero de telephone de l'entreprise");
            errors.addAll(AdresseValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(entrepriseDto.getNom())) {
            errors.add("Veuillez saisir le nom de l'entreprise");
        }

        if (!StringUtils.hasLength(entrepriseDto.getDescription())) {
            errors.add("Veuillez saisir la description de l'entreprise");
        }

        if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())) {
            errors.add("Veuillez saisir le code fiscal de l'entreprise");
        }

        if (!StringUtils.hasLength(entrepriseDto.getEmail())) {
            errors.add("Veuillez saisir l'email de l'entreprise");
        }

        if (!StringUtils.hasLength(entrepriseDto.getTelephone())) {
            errors.add("Veuillez saisir le numero de telephone de l'entreprise");
        }

        errors.addAll(AdresseValidator.validate(entrepriseDto.getAdresse()));
        return errors;
    }
}
