package fr.gestiondestock.dto;

import java.math.BigDecimal;

import fr.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@SuppressWarnings("unused")
public class ArticleDto {
	
	/***--------------------- Attribut(s) ---------------------***/

	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String codeArticle;
	
	private String designation;
	
	private BigDecimal prixUnitaireHt;
	
	private BigDecimal tauxTva;
	
	private BigDecimal prixUnitaireTtc;
	
	private String photo;
	
	/* Attribut(s) classe(s) */
	
	// => Article <-> Category
	private CategoryDto category;

}
