package fr.gestiondestock.services;

import fr.gestiondestock.dto.ArticleDto;
import fr.gestiondestock.dto.LigneCommandeClientDto;
import fr.gestiondestock.dto.LigneCommandeFournisseurDto;
import fr.gestiondestock.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Integer id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

    List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);

    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

    List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

    List<ArticleDto> findAll();

    void delete(Integer id);

}
