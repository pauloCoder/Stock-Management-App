package fr.gestiondestock.model;

import javax.persistence.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Roles")
public class Roles extends AbstractEntity
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
