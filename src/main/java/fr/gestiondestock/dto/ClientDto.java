package fr.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
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
	private Integer idEntreprise;

	/* Attribut(s) classe(s) */
	
	// => Client <-> Adresse
	private AdresseDto adresse;

	// => Client <-> CommandeClient
	@JsonIgnore
	private List<CommandeClientDto> commandeClients;

	public static ClientDto fromEntity(Client client) {

		if (client == null) {
			return null;
		}

		return ClientDto.builder()
						.id(client.getId())
						.nom(client.getNom())
						.prenom(client.toString())
						.photo(client.getPhoto())
						.email(client.getEmail())
						.telephone(client.getTelephone())
						.idEntreprise(client.getIdEntreprise())
						.adresse(AdresseDto.fromEntity(client.getAdresse()))
						.build();
	}

	public static Client toEntity(ClientDto clientDto) {

		if (clientDto == null) {
			return null;
		}

		Client client = new Client();
		client.setId(clientDto.getId());
		client.setNom(clientDto.getNom());
		client.setPrenom(clientDto.getPrenom());
		client.setPhoto(clientDto.getPhoto());
		client.setEmail(clientDto.getEmail());
		client.setTelephone(clientDto.getTelephone());
		client.setIdEntreprise(clientDto.getIdEntreprise());
		client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));

		return client;

	}


}
