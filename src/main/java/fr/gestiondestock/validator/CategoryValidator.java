package fr.gestiondestock.validator;

import fr.gestiondestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(final CategoryDto categoryDto) {

        List<String> errors = new ArrayList<>();

        if (categoryDto == null || StringUtils.hasLength(categoryDto.getCodeCategorie())) {
            errors.add("Veuillez saisir le code catégorie");
        }

        return errors;
    }
}
