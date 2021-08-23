package fr.gestiondestock.dto;

import fr.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
	
	/* Attribut(s) classe(s) */
	
	// => LigneCommandeClient <-> Article
	private ArticleDto article;
	
	// => LigneCommandeClient <-> CommandeClient
	private CommandeClientDto commandeClient; //TODO : remove after

	/***--------------------- Mapping ---------------------***/

	public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {

		if (ligneCommandeClient == null) {
			return null;
		}

		return LigneCommandeClientDto.builder()
									 .id(ligneCommandeClient.getId())
									 .quantite(ligneCommandeClient.getQuantite())
									 .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
									 .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
									 .commandeClient(CommandeClientDto.fromEntity(ligneCommandeClient.getCommandeClient()))
									 .build();
	}

	public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {

		if (ligneCommandeClientDto == null) {
			return null;
		}

		LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
		ligneCommandeClient.setId(ligneCommandeClientDto.getId());
		ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
		ligneCommandeClient.setPrixUnitaire(ligneCommandeClient.getPrixUnitaire());
		ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
		ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeClient()));

		return ligneCommandeClient;

}

}
