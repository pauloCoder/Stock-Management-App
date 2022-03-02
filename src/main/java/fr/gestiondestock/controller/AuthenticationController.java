package fr.gestiondestock.controller;

import fr.gestiondestock.dto.auth.AuthenticationRequest;
import fr.gestiondestock.dto.auth.AuthenticationResponse;
import fr.gestiondestock.model.auth.ExtendedUser;
import fr.gestiondestock.services.auth.ApplicationUserDetailsService;
import fr.gestiondestock.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.gestiondestock.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(value = AUTHENTICATION_ENDPOINT)
public class AuthenticationController {

    private final ApplicationUserDetailsService applicationUserDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationController(ApplicationUserDetailsService applicationUserDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(),authenticationRequest.getPassword())
        );

       final UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(authenticationRequest.getLogin());

       final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

       return ResponseEntity.ok((AuthenticationResponse.builder().accessToken(jwt).build()));
    }
}
