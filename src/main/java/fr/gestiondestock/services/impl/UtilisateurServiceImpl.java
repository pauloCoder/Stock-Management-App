package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.UtilisateurDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.Utilisateur;
import fr.gestiondestock.repository.UtilisateurRepository;
import fr.gestiondestock.services.UtilisateurService;
import fr.gestiondestock.validator.ClientValidator;
import fr.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid {}",utilisateurDto);
            throw new EntityNotValidException( "L'utilisateur' n'est pas valide" , ErrorCodes.UTILISATEUR_NOT_VALID , errors);
        }

        Utilisateur utilisateurSaved = utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto));
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
                    log.error("Inexistant utilisateur for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucun utilisateur avec l'ID %s n'a ete trouve dans la BDD",id) ,ErrorCodes.UTILISATEUR_NOT_FOUND);
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
                    log.error("Inexistant utilisateur for email {}",email);
                    throw new EntityNotFoundException(String.format("Aucun utilisateur avec l'EMAIL %s n'a ete trouve dans la BDD",email) ,ErrorCodes.UTILISATEUR_NOT_FOUND);
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

}
