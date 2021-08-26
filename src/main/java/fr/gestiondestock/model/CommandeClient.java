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
@Table(name = "commande_client")
public class CommandeClient extends AbstractEntity
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code_commande")
	private String codeCommande;
	
	@Column(name = "date_commande")
	private Instant dateCommande;

	@Column(name = "etat_commande")
	@Enumerated(EnumType.STRING)
	private EtatCommande etatCommande;

	@Column(name = "entreprise_id")
	private Integer idEntreprise;
	
	/* Attribut(s) classe(s) */
	
	// => CommandeClient <-> LigneCommandeClient
	@OneToMany(mappedBy = "commandeClient")
	private List<LigneCommandeClient> ligneCommandeClients;
	
	// => CommandeClient <-> Client
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
}
