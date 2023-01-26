package fr.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	private String roleName;
		
	/* Attribut(s) classe(s) */
	
	// => Role <-> Utilisateur
	@JsonIgnore
	UtilisateurDto utilisateur;

	public static RolesDto fromEntity(Roles roles) {

		return RolesDto.builder()
					   .id(roles.getId())
					   .roleName(roles.getRoleName())
					   .build();
	}

	public static Roles toEntity(RolesDto rolesDto)
	{
		Roles roles = new Roles();
		roles.setId(rolesDto.getId());
		roles.setRoleName(rolesDto.getRoleName());
		roles.setUtilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateur()));

		return roles;
	}

}
