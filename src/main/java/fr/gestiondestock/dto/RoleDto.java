package fr.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class RoleDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String roleName;
		
	/* Attribut(s) classe(s) */
	
	// => Role <-> Utilisateur 
	UtilisateurDto utilisateur;

}
