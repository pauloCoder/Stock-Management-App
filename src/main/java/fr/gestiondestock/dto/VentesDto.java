package fr.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VentesDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;

	private String codeVente;
	
	private Instant dateVente;
	
	private String commentaire;

	private Integer idEntreprise;

	/* Attribut(s) classe(s) */
	
	// => Vente <-> LigneVente
	@JsonIgnore
	private List<LigneVenteDto> ligneVentes;

	/***--------------------- Mapping ---------------------***/

	public static VentesDto fromEntity(Ventes ventes) {

		if (ventes == null) {
			return null;
		}

		return VentesDto.builder()
						.id(ventes.getId())
						.codeVente(ventes.getCodeVente())
						.dateVente(ventes.getDateVente())
						.commentaire(ventes.getCommentaire())
						.idEntreprise(ventes.getIdEntreprise())
						.build();

	}

	public static Ventes toEntity(VentesDto ventesDto) {

		if (ventesDto == null) {
			return null;
		}

		Ventes ventes = new Ventes();
		ventes.setId(ventesDto.getId());
		ventes.setCodeVente(ventesDto.getCodeVente());
		ventes.setDateVente(ventesDto.getDateVente());
		ventes.setCommentaire(ventesDto.getCommentaire());
		ventes.setIdEntreprise(ventesDto.getIdEntreprise());

		return ventes;

	}


}
