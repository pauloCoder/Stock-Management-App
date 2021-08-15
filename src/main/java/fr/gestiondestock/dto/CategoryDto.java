package fr.gestiondestock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.gestiondestock.model.Article;
import fr.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@SuppressWarnings("unused")
public class CategoryDto {
	
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String codeCategorie;
	
	private String designation;
	
	/* Attribut(s) classe(s) */
	
	// => Category <-> Article
	@JsonIgnore
	private List<ArticleDto> articles;
	
	/***--------------------- Mapping ---------------------***/
	
	public CategoryDto fromEntity(Category category)
	{
		if (category == null){
			return null;
			//TODO throw an exception
		}
		
		return CategoryDto.builder()
						  .id(category.getId())
						  .codeCategorie(category.getCodeCategorie())
						  .designation(category.getDesignation())
						  .build();
	}


}
