package fr.gestiondestock.model;

import java.time.Instant;
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
@Table(name = "commande_fournisseur")
public class CommandeFournisseur extends AbstractEntity
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private static final long serialVersionUID = 1L;
	
	@Column(name = "code_commande")
	private String codeCommande;
	
	@Column(name = "date_commande")
	private Instant dateCommande;
	
	/* Attribut(s) classe(s) */
	
	// => CommandeFournisseur <-> Fournisseur
	@ManyToOne
	@JoinColumn(name = "fournisseur_id")
	private Fournisseur fournisseur;
	
	// => CommandeFournisseur <-> LigneCommandeFournisseur
	@OneToMany(mappedBy = "commandeFournisseur")
	private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

}
