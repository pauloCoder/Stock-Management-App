package fr.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto
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
	
	// => Fournisseur <-> Adresse
	private AdresseDto adresse;
	
	// => Fournisseur <-> CommandeFournisseur
	@JsonIgnore
	private List<CommandeFournisseurDto> commandeFournisseurs;

	/***--------------------- Attribut(s) ---------------------***/

	public static FournisseurDto fromEntity(Fournisseur fournisseur) {

		if (fournisseur == null) {
			return null;
		}

		return FournisseurDto.builder()
						 	 .id(fournisseur.getId())
							 .nom(fournisseur.getNom())
							 .prenom(fournisseur.getPrenom())
							 .photo(fournisseur.getPhoto())
							 .email(fournisseur.getEmail())
							 .telephone(fournisseur.getTelephone())
							 .idEntreprise(fournisseur.getIdEntreprise())
							 .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
							 .build();
	}

	public static Fournisseur toEntity(FournisseurDto fournisseurDto) {

		if (fournisseurDto == null) {
			return  null;
		}

		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setId(fournisseurDto.getId());
		fournisseur.setNom(fournisseurDto.getNom());
		fournisseur.setPrenom(fournisseurDto.getPrenom());
		fournisseur.setPhoto(fournisseurDto.getPhoto());
		fournisseur.setEmail(fournisseurDto.getEmail());
		fournisseur.setTelephone(fournisseurDto.getTelephone());
		fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());
		fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresse()));

		return fournisseur;
	}

}
