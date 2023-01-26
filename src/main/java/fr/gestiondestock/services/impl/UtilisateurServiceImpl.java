package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import fr.gestiondestock.dto.UtilisateurDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.exception.InvalidOperationException;
import fr.gestiondestock.model.Utilisateur;
import fr.gestiondestock.repository.UtilisateurRepository;
import fr.gestiondestock.services.UtilisateurService;
import fr.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto changerMotDePasseUtilisateurDto) {

        List<String> errors = UtilisateurValidator.validate(changerMotDePasseUtilisateurDto);
        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid {}",changerMotDePasseUtilisateurDto);
            throw new EntityNotValidException( "L'utilisateur' n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID , errors);
        }

        Utilisateur utilisateurSaved = utilisateurRepository.save(UtilisateurDto.toEntity(changerMotDePasseUtilisateurDto));
        return  UtilisateurDto.fromEntity(utilisateurSaved);

    }

    @Override
    public UtilisateurDto findById(Integer id) {

        if (id == null) {
            log.error("Utilisateur ID is null");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return UtilisateurDto.fromEntity(
                utilisateur.orElseThrow( () -> {
                    log.error("Inexistant utilisateur for id {}", id);
                    throw new EntityNotFoundException(String.format("Aucun utilisateur avec l'ID %s n'a ete trouve dans la BDD",id), ErrorCodes.UTILISATEUR_NOT_FOUND);
                })
        );

    }

    @Override
    public UtilisateurDto findByEmail(String email) {

        if (email == null) {
            log.error("Utilisateur EMAIL is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        return UtilisateurDto.fromEntity(
                utilisateur.orElseThrow( () -> {
                    log.error("Inexistant utilisateur for email {}", email);
                    throw new EntityNotFoundException(String.format("Aucun utilisateur avec l'EMAIL %s n'a ete trouve dans la BDD", email), ErrorCodes.UTILISATEUR_NOT_FOUND);
                })
        );

    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }

        utilisateurRepository.deleteById(id);

    }

    @Override
    public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto) {
       validate(changerMotDePasseUtilisateurDto);
       Integer id = changerMotDePasseUtilisateurDto.getId();
       Utilisateur utilisateur = utilisateurRepository.findById(id)
               .orElseThrow(() -> {
                   log.warn("Aucun utilisateur n'a été trouvé dans la BDD avec l'id {}", id);
                   throw new EntityNotFoundException(String.format("Aucun utilisateur n'a été trouvé dans la BDD avec l'id %s", id), ErrorCodes.UTILISATEUR_NOT_FOUND);
               });
       utilisateur.setMotDePasse(changerMotDePasseUtilisateurDto.getMotDePasse());
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(utilisateur)
        );
    }

    private void validate(ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto) {
        if (changerMotDePasseUtilisateurDto == null) {
            log.warn("Impossible de modifier le mot de passe avec un objet null");
            throw new InvalidOperationException("Aucune information n'a été fourni pour modifier le mot de passe", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (changerMotDePasseUtilisateurDto.getId() == null) {
            log.warn("Impossible de modifier le mot de passe avec un ID null");
            throw new InvalidOperationException("ID utilisateur null. Impossible pour modifier le mot de passe", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

        if (!StringUtils.hasLength(changerMotDePasseUtilisateurDto.getMotDePasse()) || !StringUtils.hasLength(changerMotDePasseUtilisateurDto.getConfirmeMotDePasse())) {
            log.warn("Impossible de modifier le mot de passe avec un mot de passe vide");
            throw new InvalidOperationException("Mot de passe utilisateur null. Impossible pour modifier le mot de passe", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!changerMotDePasseUtilisateurDto.getMotDePasse().equals(changerMotDePasseUtilisateurDto.getConfirmeMotDePasse())) {
            log.warn("Impossible de modifier le mot de passe avec deux mots de passe différents");
            throw new InvalidOperationException("Mot de passe utilisateur non conformes. Impossible pour modifier le mot de passe", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
    }

}
