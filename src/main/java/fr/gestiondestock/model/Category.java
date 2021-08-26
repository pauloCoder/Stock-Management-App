package fr.gestiondestock.model;

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
@Table(name = "category")
public class Category extends AbstractEntity
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code_categorie")
	private String codeCategorie;
	
	@Column(name = "designation")
	private String designation;

	@Column(name = "entreprise_id")
	private Integer idEntreprise;
	
	/* Attribut(s) classe(s) */
	
	// => Category <-> Article
	@OneToMany(mappedBy = "category")
	private List<Article> articles;

}
