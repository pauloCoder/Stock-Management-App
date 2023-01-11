package fr.gestiondestock.repository;

import fr.gestiondestock.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {

    List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer idCommandeFournisseur);
    List<LigneCommandeFournisseur> findAllByArticleId(Integer idArticle);

}
