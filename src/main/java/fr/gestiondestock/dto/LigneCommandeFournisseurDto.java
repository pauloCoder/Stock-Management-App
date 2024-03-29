package fr.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	private BigDecimal quantite;
	private BigDecimal prixUnitaire;
	private Integer idEntreprise;

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
				.idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
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
		ligneCommandeFournisseur.setIdEntreprise(ligneCommandeFournisseurDto.getIdEntreprise());
		ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));

		return ligneCommandeFournisseur;

	}


}
