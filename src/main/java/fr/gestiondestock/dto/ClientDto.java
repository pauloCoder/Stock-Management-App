package fr.gestiondestock.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@SuppressWarnings("unused")
public class ClientDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String nom;
	
	private String prenom;
	
	private String photo;
	
	private String email;
	
	private String telephone;
	
	/* Attribut(s) classe(s) */
	
	// => Client <-> Adresse
	private AdresseDto adresse;

	// => Client <-> CommandeClient
	private List<CommandeClientDto> commandeClients;


}
