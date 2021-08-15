package fr.gestiondestock.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	/* Attribut(s) classe(s) */
	
	// => Article <-> Category
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
}
