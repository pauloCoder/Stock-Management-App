package fr.gestiondestock.dto;

import java.time.Instant;
import java.util.List;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class CommandeFournisseurDto
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String codeCommande;
	
	private Instant dateCommande;
	
	/* Attribut(s) classe(s) */
	
	// => CommandeFournisseur <-> Fournisseur
	private FournisseurDto fournisseur;
	
	// => CommandeFournisseur <-> LigneCommandeFournisseur
	private List<LigneCommandeClientDto> ligneCommandeFournisseurs;

}
