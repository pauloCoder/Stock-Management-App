package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.PhotoApi;
import fr.gestiondestock.services.strategy.impl.StrategyPhotoContext;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoController implements PhotoApi {

    private final StrategyPhotoContext strategyPhotoContext;

    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @SneakyThrows
    @Override
    public Object savePhoto(String context, Integer id, MultipartFile photo, String titre) {
        return strategyPhotoContext.savePhoto(context, id, photo.getInputStream(), titre);
    }
}
