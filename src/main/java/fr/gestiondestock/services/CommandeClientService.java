package fr.gestiondestock.services;

import fr.gestiondestock.dto.CommandeClientDto;
import fr.gestiondestock.dto.LigneCommandeClientDto;
import fr.gestiondestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    void deleteById(Integer id);

    CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeClientDto updateClient(Integer idCommande, Integer idClient);

    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);

    //Delete article ==> delete LigneCommandeClient
    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);

    List<LigneCommandeClientDto> findAllLignesCommandesClientByIdCommandeClient(Integer idCommande);

}
