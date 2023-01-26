package fr.gestiondestock.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import fr.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurDto 
{

	/***--------------------- Attribut(s) ---------------------***/
	
	/* Attribut(s) primitif(s) */
	
	private Integer id;
	private String nom;
	private String prenom;
	private String photo;
	private Instant dateDeNaissance;
	private String email;
	private String telephone;
	private String motDePasse;
	
	/* Attribut(s) classe(s) */
	
	// => Utilisateur <-> Adresse
	private AdresseDto adresse;
	
	// => Utilisateur <-> Entreprise
	private EntrepriseDto entreprise;
	
	// => Utilisateur <-> Role
	private List<RolesDto> roles;

	/***--------------------- Mapping ---------------------***/

	public static UtilisateurDto fromEntity(Utilisateur utilisateur) {

		if (utilisateur == null) {
			return null;
		}

		return UtilisateurDto.builder()
				.id(utilisateur.getId())
				.nom(utilisateur.getNom())
				.prenom(utilisateur.getPrenom())
				.photo(utilisateur.getPhoto())
				.dateDeNaissance(utilisateur.getDateDeNaissance())
				.email(utilisateur.getEmail())
				.telephone(utilisateur.getTelephone())
				.motDePasse(utilisateur.getMotDePasse())
				.adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
				.entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
				.roles(
						utilisateur.getRoles() != null ?
								utilisateur.getRoles().stream()
										   .map(RolesDto::fromEntity)
										   .collect(Collectors.toList()) : null
				)
				.build();

	}

	public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {

		if (utilisateurDto == null) {
			return null;
		}

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(utilisateurDto.getId());
		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setPrenom(utilisateurDto.getPrenom());
		utilisateur.setPhoto(utilisateurDto.getPhoto());
		utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
		utilisateur.setEmail(utilisateurDto.getEmail());
		utilisateur.setTelephone(utilisateurDto.getTelephone());
		utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
		utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
		utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
		utilisateur.setRoles(
								utilisateurDto.getRoles() != null ?
										utilisateurDto.getRoles().stream()
												      .map(RolesDto::toEntity)
												      .collect(Collectors.toList()) : null
		);

		return utilisateur;
	}

}
