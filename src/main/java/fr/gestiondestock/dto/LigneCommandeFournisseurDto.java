package fr.gestiondestock.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.LigneCommandeClient;
import fr.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
	@JsonIgnore
	private CommandeFournisseurDto commandeFournisseur;

	/***--------------------- Mapping ---------------------***/

	public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {

		if (ligneCommandeFournisseur == null) {
			return null;
		}

		return LigneCommandeFournisseurDto.builder()
				.id(ligneCommandeFournisseur.getId())
				.quantite(ligneCommandeFournisseur.getQuantite())
				.prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
				.article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
				.build();
	}

	public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {

		if (ligneCommandeFournisseurDto == null) {
			return null;
		}

		LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
		ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
		ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
		ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());
		ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));

		return ligneCommandeFournisseur;

	}


}
