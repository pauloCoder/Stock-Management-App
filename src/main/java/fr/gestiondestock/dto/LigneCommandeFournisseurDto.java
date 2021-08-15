package fr.gestiondestock.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class LigneCommandeFournisseurDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
	
	
	/* Attribut(s) classe(s) */
	
	// => LigneCommandeFournisseur <-> Article
	private ArticleDto article;
	
	// => LigneCommandeFournisseur <-> CommandeFournisseur
	private CommandeFournisseurDto commandeFournisseur;

}
