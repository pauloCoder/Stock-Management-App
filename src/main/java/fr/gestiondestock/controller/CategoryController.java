package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.CategoryApi;
import fr.gestiondestock.dto.CategoryDto;
import fr.gestiondestock.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<CategoryDto> save(CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @Override
    public ResponseEntity<CategoryDto> findById(Integer id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Override
    public ResponseEntity<CategoryDto> findByCode(String codeCategory) {
        return ResponseEntity.ok(categoryService.findByCodeCategory(codeCategory));
    }

    @Override
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
