package fr.gestiondestock.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "ventes")
public class Ventes extends AbstractEntity
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code_vente")
	private String codeVente;
	
	@Column(name = "date_vente")
	private Instant dateVente;
	
	@Column(name = "commentaire")
	private String commentaire;
	
	/* Attribut(s) classe(s) */
	
	// => Vente <-> LigneVente
	@OneToMany(mappedBy = "ventes")
	private List<LigneVente> ligneVentes;

}
