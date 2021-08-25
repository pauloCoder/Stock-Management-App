package fr.gestiondestock.repository;

import fr.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

interface ArticleRepository extends JpaRepository<Article, Integer> {

}
