package fr.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class UtilisateurDto 
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private Integer id;

	private String nom;
	
	private String prenom;	
	
	private String photo;
	
	private Instant dateDeNaissance;
	
	private String email;
	
	private String telephone;
	
	private String motDePasse;
	
	/* Attribut(s) classe(s) */
	
	// => Utilisateur <-> Adresse
	private AdresseDto adresse;
	
	// => Utilisateur <-> Entreprise
	private EntrepriseDto entreprise;
	
	// => Utilisateur <-> Role
	private List<RoleDto> roles;
}
