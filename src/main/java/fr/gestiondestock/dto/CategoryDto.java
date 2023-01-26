package fr.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	private String codeCategorie;
	private String designation;
	private Integer idEntreprise;

	/* Attribut(s) classe(s) */

	// => Category <-> Article
	@JsonIgnore
	private List<ArticleDto> articles;
	
	/***--------------------- Mapping ---------------------***/
	
	public static CategoryDto fromEntity(Category category) {
		if (category == null){
			return null;
			//TODO throw an exception
		}

		return CategoryDto.builder()
						  .id(category.getId())
						  .codeCategorie(category.getCodeCategorie())
						  .designation(category.getDesignation())
						  .idEntreprise(category.getIdEntreprise())
						  .build();
	}

	public static Category toEntity(CategoryDto categoryDto) {

		if (categoryDto == null) {
			return null;
		}

		Category category = new Category();
		category.setId(categoryDto.getId());
		category.setCodeCategorie(categoryDto.getCodeCategorie());
		category.setDesignation(categoryDto.getDesignation());
		category.setIdEntreprise(categoryDto.getIdEntreprise());

		return category;

	}

}
