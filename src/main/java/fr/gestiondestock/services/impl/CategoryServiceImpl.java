package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.CategoryDto;
import fr.gestiondestock.dto.CategoryDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.Category;
import fr.gestiondestock.model.Category;
import fr.gestiondestock.repository.CategoryRepository;
import fr.gestiondestock.repository.CategoryRepository;
import fr.gestiondestock.services.CategoryService;
import fr.gestiondestock.validator.ArticleValidator;
import fr.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        List<String> errors = CategoryValidator.validate(categoryDto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}",categoryDto);
            throw new EntityNotValidException( "La category n'est pas valide" , ErrorCodes.CATEGORY_NOT_VALID , errors);
        }

        Category savedCategory = categoryRepository.save(CategoryDto.toEntity(categoryDto));
        return CategoryDto.fromEntity(savedCategory);

    }

    @Override
    public CategoryDto findById(Integer id) {

        if (id == null) {
            log.error("Category ID is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findById(id);
        return CategoryDto.fromEntity(
                category.orElseThrow( () -> {
                    log.error("Inexistant category for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucune category avec l'ID %s n'a ete trouvee dans la BDD",id) ,ErrorCodes.CATEGORY_NOT_FOUND);
                } )
        );

    }

    @Override
    public CategoryDto findByCodeCategory(String codeCategory) {
        if (StringUtils.hasLength(codeCategory)) {
            log.error("Category code is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findCategoryByCodeCategorie(codeCategory);
        return CategoryDto.fromEntity(
                category.orElseThrow( () -> {
                    log.error("Inexistant category for code {}",codeCategory);
                    throw new EntityNotFoundException(String.format("Aucune category avec le CODE %s n'a été trouvé dans la BDD",codeCategory) ,ErrorCodes.CATEGORY_NOT_FOUND);
                } )
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(CategoryDto::fromEntity)
                                 .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            log.error("Category ID is null");
            return;
        }

        categoryRepository.deleteById(id);

    }

}
