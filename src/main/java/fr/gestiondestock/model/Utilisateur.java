package fr.gestiondestock.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "utilisateur")
public class Utilisateur extends AbstractEntity
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
	
	@Column(name = "date_de_naissance")
	private Instant dateDeNaissance;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "mot_de_passe")
	private String motDePasse;
	
	/* Attribut(s) classe(s) */
	
	// => Utilisateur <-> Adresse
	@Embedded
	private Adresse adresse;
	
	// => Utilisateur <-> Entreprise
	@ManyToOne
	@JoinColumn(name = "entreprise_id")
	private Entreprise entreprise;
	
	// => Utilisateur <-> Role
	@OneToMany(fetch = FetchType.EAGER , mappedBy = "utilisateur")
	@JsonIgnore
	private List<Roles> roles;
}
