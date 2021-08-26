package fr.gestiondestock.model;

import java.util.List;

import javax.persistence.*;

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
@Table(name = "fournisseur")
public class Fournisseur extends AbstractEntity
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telephone")
	private String telephone;

	@Column(name = "entreprise_id")
	private Integer idEntreprise;
	
	/* Attribut(s) classe(s) */
	
	// => Fournisseur <-> Adresse
	@Embedded
	private Adresse adresse;
	
	// => Fournisseur <-> CommandeFournisseur
	@OneToMany(mappedBy = "fournisseur")
	private List<CommandeFournisseur> commandeFournisseurs;

}
