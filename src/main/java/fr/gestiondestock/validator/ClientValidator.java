package fr.gestiondestock.validator;

import fr.gestiondestock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(final ClientDto clientDto) {

        List<String> errors = new ArrayList<>();

        if (clientDto == null) {
            errors.add("Veuillez saisir le nom du client");
            errors.add("Veuillez saisir le prenom du client");
            errors.add("Veuillez saisir l'email du client'");
            errors.add("Veuillez saisir le téléphone du client");
            return errors;
        }

        if (!StringUtils.hasLength(clientDto.getNom())) {
            errors.add("Veuillez saisir le nom du client");
        }

        if (!StringUtils.hasLength(clientDto.getPrenom())) {
            errors.add("Veuillez saisir le prenom du client");
        }

        if (!StringUtils.hasLength(clientDto.getEmail())) {
            errors.add("Veuillez saisir l'email du client");
        }

        if (!StringUtils.hasLength(clientDto.getTelephone())) {
            errors.add("Veuillez saisir le téléphone du client");
        }

        return errors;

    }
}
