package fr.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("unused")
public class CommandeClientDto
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String codeCommande;
	
	private Instant dateCommande;
	
	/* Attribut(s) classe(s) */
	
	// => CommandeClient <-> LigneCommandeClient
	private List<LigneCommandeClientDto> ligneCommandeClients;
	
	// => CommandeClient <-> Client
	private ClientDto client;
}
