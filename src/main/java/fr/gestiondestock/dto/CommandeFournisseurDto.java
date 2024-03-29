package fr.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.CommandeFournisseur;
import fr.gestiondestock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto
{
	
	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	private String codeCommande;
	private Instant dateCommande;
	private EtatCommande etatCommande;
	private Integer idEntreprise;

	/* Attribut(s) classe(s) */
	
	// => CommandeFournisseur <-> Fournisseur
	private FournisseurDto fournisseur;
	
	// => CommandeFournisseur <-> LigneCommandeFournisseur
	@JsonIgnore
	private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

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
				.etatCommande(commandeFournisseur.getEtatCommande())
				.idEntreprise(commandeFournisseur.getIdEntreprise())
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
		commandeFournisseur.setEtatCommande(commandeFournisseurDto.getEtatCommande());
		commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
		commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()));

		return commandeFournisseur;

	}

	public boolean isCommandeLivree() {
		return EtatCommande.LIVREE.equals(this.getEtatCommande());
	}

}
