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
            errors.addAll(ClientValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(commandeClientDto.getCodeCommande())) {
            errors.add("Veuillez saisir le code de la commande");
        }

        if (commandeClientDto.getDateCommande() == null) {
            errors.add("Veuillez saisir la date de la commande");
        }

        if (commandeClientDto.getClient() == null) {
            errors.addAll(ClientValidator.validate(null));
        }

        return errors;

    }
}
