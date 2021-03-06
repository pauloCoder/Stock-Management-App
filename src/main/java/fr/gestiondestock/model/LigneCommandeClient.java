package fr.gestiondestock.model;

import java.math.BigDecimal;

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
@Table(name = "ligne_commande_client")
public class LigneCommandeClient extends AbstractEntity
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "quantite")
	private BigDecimal quantite;
	
	@Column(name = "prix_unitaire")
	private BigDecimal prixUnitaire;

	@Column(name = "entreprise_id")
	private Integer idEntreprise;
	
	/* Attribut(s) classe(s) */
	
	// => LigneCommandeClient <-> Article
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;
	
	// => LigneCommandeClient <-> CommandeClient
	@ManyToOne
	@JoinColumn(name = "commande_client_id")
	private CommandeClient commandeClient;
	
}
