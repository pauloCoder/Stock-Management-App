package fr.gestiondestock.model;

import java.math.BigDecimal;
import java.time.Instant;

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
@Table(name = "mvt_stock")
public class MvtStock extends AbstractEntity
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;

	@Column(name = "date_mvt")
	private Instant dateMvt;
	
	@Column(name = "quantite")
	private BigDecimal quantite;
	
	@Column(name = "type_mvt")
	@Enumerated
	private TypeMtvStock typeMvt;
	
	/* Attribut(s) classe(s) */
	
	// => MvtStock <-> Article
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;
	
}
