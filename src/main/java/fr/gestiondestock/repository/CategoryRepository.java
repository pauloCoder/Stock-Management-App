package fr.gestiondestock.repository;

import fr.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Integer> {
}
