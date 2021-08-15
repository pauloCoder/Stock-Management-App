package fr.gestiondestock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Role extends AbstractEntity
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	@Column(name = "role_name")
	private String roleName;
	
	private static final long serialVersionUID = 1L;
	
	/* Attribut(s) classe(s) */
	
	// => Role <-> Utilisateur 
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	Utilisateur utilisateur;

}
