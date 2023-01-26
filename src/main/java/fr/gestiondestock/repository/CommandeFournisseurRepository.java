package fr.gestiondestock.repository;

import fr.gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

    Optional<CommandeFournisseur> findCommandeFournisseurByCodeCommande(String code);
    List<CommandeFournisseur> findAllByFournisseurId(Integer id);

}
