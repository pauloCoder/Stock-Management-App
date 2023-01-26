package fr.gestiondestock.dto;

import fr.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
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
	private Integer idEntreprise;

	/* Attribut(s) classe(s) */
	
	// => Article <-> Category
	private CategoryDto category;

	/***--------------------- Mapping ---------------------***/

	public static ArticleDto fromEntity(Article article) {

		if (article == null) {
			//TODO : throw exception
			return null;
		}

		return ArticleDto.builder()
						 .id(article.getId())
						 .codeArticle(article.getCodeArticle())
						 .designation(article.getDesignation())
						 .prixUnitaireHt(article.getPrixUnitaireHt())
						 .tauxTva(article.getTauxTva())
					 	 .prixUnitaireTtc(article.getPrixUnitaireTtc())
						 .photo(article.getPhoto())
				         .idEntreprise(article.getIdEntreprise())
						 .category(CategoryDto.fromEntity(article.getCategory()))
				         .build();
	}

	public static Article toEntity(ArticleDto articleDto) {

		if (articleDto == null) {
			//TODO : throw exception
			return null;
		}

		Article article = new Article();

		article.setId(articleDto.getId());
		article.setCodeArticle(articleDto.getCodeArticle());
		article.setDesignation(articleDto.getDesignation());
		article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
		article.setTauxTva(articleDto.getTauxTva());
		article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
		article.setPhoto(articleDto.getPhoto());
		article.setIdEntreprise(articleDto.getIdEntreprise());
		article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));

		return article;
	}

}
