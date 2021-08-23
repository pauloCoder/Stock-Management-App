package fr.gestiondestock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntrepriseDto
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */

	private Integer id;
	
	private String nom;
	
	private String codeFiscal;
	
	private String siteWeb;
	
	private String description;
	
	private String photo;
	
	private String email;
	
	private String telephone;
	
	/* Attribut(s) classe(s) */
	
	// => Entreprise <-> Adresse
	private AdresseDto adresse;
	
	// => Entreprise <-> Utilisateur
	@JsonIgnore
	List<UtilisateurDto> utilisateurs;

	/***--------------------- Mapping ---------------------***/

	public static EntrepriseDto fromEntity(Entreprise entreprise) {

		if (entreprise == null) {
			return null;
		}

		return EntrepriseDto.builder()
				.id(entreprise.getId())
				.nom(entreprise.getNom())
				.codeFiscal(entreprise.getCodeFiscal())
				.siteWeb(entreprise.getSiteWeb())
				.description(entreprise.getDescription())
				.photo(entreprise.getPhoto())
				.email(entreprise.getEmail())
				.telephone(entreprise.getTelephone())
				.adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
				.build();
	}

	public static Entreprise toEntity(EntrepriseDto entrepriseDto)
	{
		if (entrepriseDto == null) {
			return null;
		}

		Entreprise entreprise = new Entreprise();
		entreprise.setId(entrepriseDto.getId());
		entreprise.setNom(entrepriseDto.getNom());
		entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
		entreprise.setSiteWeb(entrepriseDto.getSiteWeb());
		entreprise.setDescription(entrepriseDto.getDescription());
		entreprise.setPhoto(entrepriseDto.getPhoto());
		entreprise.setEmail(entrepriseDto.getEmail());
		entreprise.setTelephone(entrepriseDto.getTelephone());
		entreprise.setAdresse(AdresseDto.toEntity(entrepriseDto.getAdresse()));

		return entreprise;
	}

}
