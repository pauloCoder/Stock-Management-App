package fr.gestiondestock.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class FournisseurDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String nom;
	
	private String prenom;
	
	private String photo;
	
	private String email;
	
	private String telephone;
	
	/* Attribut(s) classe(s) */
	
	// => Fournisseur <-> Adresse
	private AdresseDto adresse;
	
	// => Fournisseur <-> CommandeFournisseur
	private List<CommandeFournisseurDto> commandeFournisseurs;

}
