package fr.gestiondestock.dto;

import java.math.BigDecimal;
import java.time.Instant;

import fr.gestiondestock.model.TypeMtvStock;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class MvtStockDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private Instant dateMvt;
	
	private BigDecimal quantite;
	
	private TypeMtvStock typeMvt;
	
	/* Attribut(s) classe(s) */
	
	// => MvtStock <-> Article
	private ArticleDto article;
	
}
