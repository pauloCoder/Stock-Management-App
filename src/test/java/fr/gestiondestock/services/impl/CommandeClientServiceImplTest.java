package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.CategoryDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static fr.gestiondestock.exception.ErrorCodes.CATEGORY_NOT_FOUND;
import static fr.gestiondestock.exception.ErrorCodes.CATEGORY_NOT_VALID;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CommandeClientServiceImplTest {

    @Autowired
    private CategoryService service;

    @Test
    public void shouldSaveCategoryWithSuccess() {
        CategoryDto expectedCategory = CategoryDto.builder()
                                                  .codeCategorie("Cat test")
                                                  .designation("Designation test")
                                                  .idEntreprise(1)
                                                  .build();
        CategoryDto savedCategory = service.save(expectedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(expectedCategory.getCodeCategorie(),savedCategory.getCodeCategorie());
        assertEquals(expectedCategory.getDesignation(),savedCategory.getDesignation());
        assertEquals(expectedCategory.getIdEntreprise(),savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldUpdateCategoryWithSuccess() {
        CategoryDto expectedCategory = CategoryDto.builder()
                                                  .codeCategorie("Cat test")
                                                  .designation("Designation test")
                                                  .idEntreprise(1)
                                                  .build();
        CategoryDto savedCategory = service.save(expectedCategory);

        CategoryDto categoryToUpdate = savedCategory;
        categoryToUpdate.setCodeCategorie("Cat update");

        savedCategory = service.save(categoryToUpdate);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(categoryToUpdate.getCodeCategorie(),savedCategory.getCodeCategorie());
        assertEquals(categoryToUpdate.getDesignation(),savedCategory.getDesignation());
        assertEquals(categoryToUpdate.getIdEntreprise(),savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldThrowEntityNotValidException() {
        CategoryDto expectedCategory = CategoryDto.builder()
                                                  .build();
        EntityNotValidException expectedException = assertThrows(EntityNotValidException.class,() -> service.save(expectedCategory));

        assertEquals(CATEGORY_NOT_VALID,expectedException.getErrorCode());
        assertEquals(1,expectedException.getErrors().size());
        assertEquals("Veuillez saisir le code catégorie",expectedException.getErrors().get(0));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void shouldThrowEntityNotFoundException() {
        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class,() -> service.findById(1));

        assertEquals(CATEGORY_NOT_FOUND,expectedException.getErrorCode());
        assertEquals(String.format("Aucune category avec l'ID %s n'a ete trouvee dans la BDD",1),expectedException.getMessage());
    }

}