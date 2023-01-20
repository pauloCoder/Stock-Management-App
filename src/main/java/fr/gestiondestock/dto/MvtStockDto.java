package fr.gestiondestock.dto;

import java.math.BigDecimal;
import java.time.Instant;

import fr.gestiondestock.model.MvtStock;
import fr.gestiondestock.model.SourceMvtStock;
import fr.gestiondestock.model.TypeMvtStock;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MvtStockDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private Instant dateMvt;
	
	private BigDecimal quantite;
	
	private TypeMvtStock typeMvt;

	private SourceMvtStock sourceMvt;

	private Integer idEntreprise;
	
	/* Attribut(s) classe(s) */
	
	// => MvtStock <-> Article
	private ArticleDto article;

	/***--------------------- Mapping ---------------------***/

	public static MvtStockDto fromEntity(MvtStock mvtStock) {

		if (mvtStock == null) {
			return null;
		}

		return MvtStockDto.builder()
						  .id(mvtStock.getId())
						  .dateMvt(mvtStock.getDateMvt())
						  .quantite(mvtStock.getQuantite())
						  .typeMvt(mvtStock.getTypeMvt())
						  .sourceMvt(mvtStock.getSourceMvt())
						  .idEntreprise(mvtStock.getIdEntreprise())
						  .article(ArticleDto.fromEntity(mvtStock.getArticle()))
						  .build();

	}

	public static MvtStock toEntity(MvtStockDto mvtStockDto) {

		if (mvtStockDto == null) {
			return null;
		}

		MvtStock mvtStock = new MvtStock();
		mvtStock.setId(mvtStockDto.getId());
		mvtStock.setDateMvt(mvtStockDto.getDateMvt());
		mvtStock.setTypeMvt(mvtStockDto.getTypeMvt());
		mvtStock.setSourceMvt(mvtStockDto.getSourceMvt());
		mvtStock.setIdEntreprise(mvtStockDto.getIdEntreprise());
		mvtStock.setArticle(ArticleDto.toEntity(mvtStockDto.getArticle()));

		return mvtStock;

	}

}
