package fr.gestiondestock.validator;

import fr.gestiondestock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidator {

    public static List<String> validate(VentesDto ventesDto) {

        List<String> errors = new ArrayList<>();

        if (ventesDto == null) {
            errors.add("Veuillez saisir le code de la vente");
            errors.add("Veuillez saisir la date de la vente");
            return errors;
        }

        if (!StringUtils.hasLength(ventesDto.getCodeVente())) {
            errors.add("Veuillez saisir le code de la vente");
        }

        if (ventesDto.getDateVente() == null) {
            errors.add("Veuillez saisir la date de la vente");
        }

        return errors;
    }
}
