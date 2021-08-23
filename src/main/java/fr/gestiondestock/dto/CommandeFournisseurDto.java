package fr.gestiondestock.dto;

import java.time.Instant;
import java.util.List;


import fr.gestiondestock.model.CommandeClient;
import fr.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeFournisseurDto
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String codeCommande;
	
	private Instant dateCommande;
	
	/* Attribut(s) classe(s) */
	
	// => CommandeFournisseur <-> Fournisseur
	private FournisseurDto fournisseur;
	
	// => CommandeFournisseur <-> LigneCommandeFournisseur
	private List<LigneCommandeClientDto> ligneCommandeFournisseurs;

	/***--------------------- Mapping ---------------------***/

	public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
		if (commandeFournisseur == null) {
			return null;
			//TODO throw an exception
		}

		return CommandeFournisseurDto.builder()
				.id(commandeFournisseur.getId())
				.codeCommande(commandeFournisseur.getCodeCommande())
				.dateCommande(commandeFournisseur.getDateCommande())
				.fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
				.build();
	}

	public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {

		if (commandeFournisseurDto == null) {
			return null;
		}

		CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
		commandeFournisseur.setId(commandeFournisseurDto.getId());
		commandeFournisseur.setCodeCommande(commandeFournisseurDto.getCodeCommande());
		commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
		commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()));

		return commandeFournisseur;

	}

}
