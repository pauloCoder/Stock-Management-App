package fr.gestiondestock.repository;

import fr.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

}
