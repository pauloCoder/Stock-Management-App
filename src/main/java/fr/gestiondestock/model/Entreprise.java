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
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "code_fiscal")
	private String codeFiscal;
	
	@Column(name = "site_web")
	private String siteWeb;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telephone")
	private String telephone;
	
	/* Attribut(s) classe(s) */
	
	// => Entreprise <-> Adresse
	@Embedded
	private Adresse adresse;
	
	// => Entreprise <-> Utilisateur
	@OneToMany(mappedBy = "entreprise")
	List<Utilisateur> utilisateurs;

}
