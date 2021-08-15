package fr.gestiondestock.model;

import java.math.BigDecimal;

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
public class LigneVente extends AbstractEntity
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "quantite")
	private BigDecimal quantite;
	
	@Column(name = "prix_unitaire")
	private BigDecimal prixUnitaire;
		
	/* Attribut(s) classe(s) */
	
	// => LigneVente <-> Article
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;
	
	// => LigneVente <-> Vente
	@ManyToOne
	@JoinColumn(name = "vente_id")
	private Vente vente;

}
