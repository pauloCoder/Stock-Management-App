package fr.gestiondestock.services;

import fr.gestiondestock.dto.LigneCommandeFournisseurDto;
import fr.gestiondestock.dto.CommandeFournisseurDto;
import fr.gestiondestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    CommandeFournisseurDto findById(Integer id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();

    void deleteById(Integer id);
    LigneCommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
    LigneCommandeFournisseurDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);
    LigneCommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);
    LigneCommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);

    //Delete article ==> delete LigneCommandeFournisseur
    LigneCommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);
    List<LigneCommandeFournisseurDto> findAllLignesCommandesClientByIdCommandeClient(Integer idCommande);
}
