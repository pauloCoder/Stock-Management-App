package fr.gestiondestock.repository;

import fr.gestiondestock.model.MvtStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStockRepository extends JpaRepository<MvtStock, Integer> {

}
