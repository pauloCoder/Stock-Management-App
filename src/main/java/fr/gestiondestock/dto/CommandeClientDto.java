package fr.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String codeCommande;
	
	private Instant dateCommande;
	
	/* Attribut(s) classe(s) */
	
	// => CommandeClient <-> LigneCommandeClient
	@JsonIgnore
	private List<LigneCommandeClientDto> ligneCommandeClients;
	
	// => CommandeClient <-> Client
	private ClientDto client;

	/***--------------------- Mapping ---------------------***/

	public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
		if (commandeClient == null) {
			return null;
			//TODO throw an exception
		}

		return CommandeClientDto.builder()
				.id(commandeClient.getId())
				.codeCommande(commandeClient.getCodeCommande())
				.dateCommande(commandeClient.getDateCommande())
				.client(ClientDto.fromEntity(commandeClient.getClient()))
				.build();
	}

	public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {

		if (commandeClientDto == null) {
			return null;
		}

		CommandeClient commandeClient = new CommandeClient();
		commandeClient.setId(commandeClientDto.getId());
		commandeClient.setCodeCommande(commandeClientDto.getCodeCommande());
		commandeClient.setDateCommande(commandeClientDto.getDateCommande());
		commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));

		return commandeClient;

	}
}
