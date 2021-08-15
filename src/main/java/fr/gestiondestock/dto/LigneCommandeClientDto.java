package fr.gestiondestock.dto;

import java.math.BigDecimal; 

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class LigneCommandeClientDto
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
	
	/* Attribut(s) classe(s) */
	
	// => LigneCommandeClient <-> Article
	private ArticleDto article;
	
	// => LigneCommandeClient <-> CommandeClient
	private CommandeClientDto commandeClient;

	

}
