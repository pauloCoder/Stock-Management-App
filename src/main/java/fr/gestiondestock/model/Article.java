package fr.gestiondestock.model;

import java.math.BigDecimal;
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
@Table(name = "article")
public class Article extends AbstractEntity
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code_article")
	private String codeArticle;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "prix_unitaire_ht")
	private BigDecimal prixUnitaireHt;
	
	@Column(name = "taux_tva")
	private BigDecimal tauxTva;
	
	@Column(name = "prix_unitaire_ttc")
	private BigDecimal prixUnitaireTtc;
	
 	@Column(name = "photo")
	private String photo;

	@Column(name = "entreprise_id")
	private Integer idEntreprise;
	
	/* Attribut(s) classe(s) */
	
	// => Article <-> Category
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "article")
	private List<LigneVente> ligneVentes;

	@OneToMany(mappedBy = "article")
	private List<LigneCommandeClient> ligneCommandeClients;

	@OneToMany(mappedBy = "article")
	private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

	@OneToMany(mappedBy = "article")
	private List<MvtStock> mvtStocks;
	
}
