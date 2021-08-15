package fr.gestiondestock.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class EntrepriseDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String nom;
	
	private String codeFiscal;
	
	private String siteWeb;
	
	private String description;
	
	private String photo;
	
	private String email;
	
	private String telephone;
	
	/* Attribut(s) classe(s) */
	
	// => Entreprise <-> Adresse
	private AdresseDto adresse;
	
	// => Entreprise <-> Utilisateur
	List<UtilisateurDto> utilisateurs;

}
