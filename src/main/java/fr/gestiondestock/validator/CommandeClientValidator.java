package fr.gestiondestock.validator;

import fr.gestiondestock.dto.ClientDto;
import fr.gestiondestock.dto.CommandeClientDto;
import fr.gestiondestock.model.Client;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto commandeClientDto) {

        List<String> errors = new ArrayList<>();

        if (commandeClientDto == null) {
            errors.add("Veuillez saisir le code de la commande");
            errors.add("Veuillez saisir la date de la commande");
            errors.add("Veuillez renseigner l'état de la commande");
            errors.add("Veuillez renseigner le client");
            return errors;
        }

        if (!StringUtils.hasLength(commandeClientDto.getCodeCommande())) {
            errors.add("Veuillez saisir le code de la commande");
        }

        if (commandeClientDto.getDateCommande() == null) {
            errors.add("Veuillez saisir la date de la commande");
        }

        if (!StringUtils.hasLength(commandeClientDto.getEtatCommande().toString())) {
            errors.add("Veuillez renseigner l'état de la commande");
        }

        if (commandeClientDto.getClient() == null || commandeClientDto.getClient().getId() == null) {
            errors.add("Veuillez reseigner le client");
        }

        return errors;

    }
}
