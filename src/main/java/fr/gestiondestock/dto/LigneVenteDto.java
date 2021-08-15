package fr.gestiondestock.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class LigneVenteDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
		
	/* Attribut(s) classe(s) */
	
	// => LigneVente <-> Article
	private ArticleDto article;
	
	// => LigneVente <-> Vente
	private VenteDto vente;

}
