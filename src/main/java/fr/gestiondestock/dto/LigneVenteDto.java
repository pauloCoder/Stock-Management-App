package fr.gestiondestock.dto;

import fr.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@SuppressWarnings("unused")
public class LigneVenteDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private BigDecimal quantite;
	private BigDecimal prixUnitaire;
	private Integer idEntreprise;

	/* Attribut(s) classe(s) */
	
	// => LigneVente <-> Article
	private ArticleDto article;
	
	// => LigneVente <-> Vente
	private VentesDto vente;

	/***--------------------- Mapping ---------------------***/

	public static LigneVenteDto fromEntity(LigneVente ligneVente) {

		if (ligneVente == null) {
			return null;
		}

		return LigneVenteDto.builder()
							.quantite(ligneVente.getQuantite())
							.prixUnitaire(ligneVente.getPrixUnitaire())
							.idEntreprise(ligneVente.getIdEntreprise())
							.article(ArticleDto.fromEntity(ligneVente.getArticle()))
							.vente(VentesDto.fromEntity(ligneVente.getVentes()))
							.build();

	}

	public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {

		if (ligneVenteDto == null) {
			return null;
		}

		LigneVente ligneVente = new LigneVente();
		ligneVente.setQuantite(ligneVenteDto.getQuantite());
		ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
		ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
		ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticle()));
		ligneVente.setVentes(VentesDto.toEntity(ligneVenteDto.getVente()));

		return ligneVente;

	}

}
