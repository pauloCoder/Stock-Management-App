package fr.gestiondestock.validator;

import fr.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto) {

        List<String> errors = new ArrayList<>();

        if (commandeFournisseurDto == null) {
            errors.add("Veuillez saisir le code de la commande");
            errors.add("Veuillez saisir la date de la commande");
            errors.add("Veuillez saisir l'état de la commande");
            errors.add("Veuillez saisir le fournisseur");
            return errors;
        }

        if (!StringUtils.hasLength(commandeFournisseurDto.getCodeCommande())) {
            errors.add("Veuillez saisir le code de la commande");
        }

        if (commandeFournisseurDto.getDateCommande() == null) {
            errors.add("Veuillez saisir la date de la commande");
        }

        if (StringUtils.hasLength(commandeFournisseurDto.getEtatCommande().toString())) {
            errors.add("Veuillez saisir l'état de la commande");
        }

        if (commandeFournisseurDto.getFournisseur() == null || commandeFournisseurDto.getFournisseur().getId() == null) {
            errors.add("Veuillez saisir le fournisseur");
        }

        return errors;

    }

}
