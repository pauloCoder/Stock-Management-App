package fr.gestiondestock.repository;

import fr.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category , Integer> {

    Optional<Category> findCategoryByCodeCategorie(String codeCategorie);

}
