package fr.gestiondestock.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {

    private String accessToken;
    /**
    private String nom;

    private String prenom;

    private String email;

    private String photo;
     */

}
