package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.EntrepriseDto;
import fr.gestiondestock.dto.RolesDto;
import fr.gestiondestock.dto.UtilisateurDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.Entreprise;
import fr.gestiondestock.repository.EntrepriseRepository;
import fr.gestiondestock.repository.RolesRepository;
import fr.gestiondestock.services.EntrepriseService;
import fr.gestiondestock.services.UtilisateurService;
import fr.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;
    private final UtilisateurService utilisateurService;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {

        List<String> errors = EntrepriseValidator.validate(entrepriseDto);

        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}",entrepriseDto);
            throw new EntityNotValidException( "L'entreprise n'est pas valide" , ErrorCodes.ENTREPRISE_NOT_VALID , errors);
        }

        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));

        UtilisateurDto utilisateurDto = fromEntreprise(savedEntreprise);

        UtilisateurDto savedUtilisateur = utilisateurService.save(utilisateurDto);

        RolesDto rolesDto = RolesDto.builder()
                                    .roleName("ADMIN")
                                    .utilisateur(savedUtilisateur)
                                    .build();

        rolesRepository.save(RolesDto.toEntity(rolesDto));

        return savedEntreprise;

    }

    private UtilisateurDto fromEntreprise(EntrepriseDto entrepriseDto) {
        return UtilisateurDto.builder()
                .adresse(entrepriseDto.getAdresse())
                .nom(entrepriseDto.getNom())
                .prenom(entrepriseDto.getCodeFiscal())
                .email(entrepriseDto.getEmail())
                .motDePasse(passwordEncoder.encode(generateRandowPassword()))
                .entreprise(entrepriseDto)
                .dateDeNaissance(Instant.now())
                .photo(entrepriseDto.getPhoto())
                .build();
    }

    private String generateRandowPassword() {
        return "som3R@nd0mP@$$word";
    }

    @Override
    public EntrepriseDto findById(Integer id) {

        if (id == null) {
            log.error("Entreprise ID is null");
            return null;
        }

        Optional<Entreprise> entrepriseDto =  entrepriseRepository.findById(id);
        return EntrepriseDto.fromEntity(
                entrepriseDto.orElseThrow(() -> {
                    log.error("Inexistant entreprise for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucune entreprise avec l'ID %s n'a ete trouvee dans la BDD",id) ,ErrorCodes.ENTREPRISE_NOT_FOUND);
                })
        );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll()
                                   .stream()
                                   .map(EntrepriseDto::fromEntity)
                                   .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            log.error("Entreprise ID is null");
        }
    }
}
