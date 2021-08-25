package fr.gestiondestock.repository;

import fr.gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

}
