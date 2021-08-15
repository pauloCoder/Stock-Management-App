package fr.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class VenteDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;

	private String codeVente;
	
	private Instant dateVente;
	
	private String commentaire;
	
	/* Attribut(s) classe(s) */
	
	// => Vente <-> LigneVente
	private List<LigneVenteDto> ligneVentes;

}
