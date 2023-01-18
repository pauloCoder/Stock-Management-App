package fr.gestiondestock.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import fr.gestiondestock.dto.FournisseurDto;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.exception.InvalidOperationException;
import fr.gestiondestock.services.FlickrService;
import fr.gestiondestock.services.FournisseurService;
import fr.gestiondestock.services.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service(value = "fournisseurStrategy")
@Slf4j
public class SaveFournisseurPhoto implements Strategy<FournisseurDto> {

    private final FournisseurService fournisseurService;
    private final FlickrService flickrService;

    @Autowired
    public SaveFournisseurPhoto(FournisseurService fournisseurService, FlickrService flickrService) {
        this.fournisseurService = fournisseurService;
        this.flickrService = flickrService;
    }

    @Override
    public FournisseurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        FournisseurDto fournisseurDto = fournisseurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo du fournisseur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        fournisseurDto.setPhoto(urlPhoto);
        return fournisseurService.save(fournisseurDto);
    }
}
