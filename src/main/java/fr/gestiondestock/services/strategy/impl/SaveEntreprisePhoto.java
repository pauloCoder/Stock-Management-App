package fr.gestiondestock.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import fr.gestiondestock.dto.EntrepriseDto;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.exception.InvalidOperationException;
import fr.gestiondestock.services.EntrepriseService;
import fr.gestiondestock.services.FlickrService;
import fr.gestiondestock.services.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service(value = "entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseDto> {

    private final EntrepriseService entrepriseService;
    private final FlickrService flickrService;

    @Autowired
    public SaveEntreprisePhoto(EntrepriseService entrepriseService, FlickrService flickrService) {
        this.entrepriseService = entrepriseService;
        this.flickrService = flickrService;
    }

    @Override
    public EntrepriseDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        EntrepriseDto entrepriseDto = entrepriseService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'entreprise", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        entrepriseDto.setPhoto(urlPhoto);
        return entrepriseService.save(entrepriseDto);
    }
}
