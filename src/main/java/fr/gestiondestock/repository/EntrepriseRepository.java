package fr.gestiondestock.repository;

import fr.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise , Integer> {

}
