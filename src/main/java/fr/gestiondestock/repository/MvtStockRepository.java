package fr.gestiondestock.repository;

import fr.gestiondestock.model.MvtStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStockRepository extends JpaRepository<MvtStock, Integer> {

    @Query("SELECT SUM(m.quantite) FROM MvtStock m " +
            "JOIN m.article a " +
            "WHERE a.id = :idArticle")
    BigDecimal stockReelArticle(@Param("idArticle") Integer idArticle);

    List<MvtStock> findAllByArticleId(Integer idArticle);

}
