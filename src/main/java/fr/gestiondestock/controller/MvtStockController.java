package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.MvtStockApi;
import fr.gestiondestock.dto.MvtStockDto;
import fr.gestiondestock.services.MvtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvtStockController implements MvtStockApi {


    private final MvtStockService mvtStockService;

    @Autowired
    public MvtStockController(MvtStockService mvtStockService) {
        this.mvtStockService = mvtStockService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return mvtStockService.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStockDto> mvtStockArticle(Integer idArticle) {
        return mvtStockService.mvtStockArticle(idArticle);
    }

    @Override
    public MvtStockDto entreeStockArticle(MvtStockDto mvtStockDto) {
        return mvtStockService.entreeStockArticle(mvtStockDto);
    }

    @Override
    public MvtStockDto sortieStockArticle(MvtStockDto mvtStockDto) {
        return mvtStockService.sortieStockArticle(mvtStockDto);
    }

    @Override
    public MvtStockDto correctionStockPositive(MvtStockDto mvtStockDto) {
        return mvtStockService.correctionStockPositive(mvtStockDto);
    }

    @Override
    public MvtStockDto correctionStockNegative(MvtStockDto mvtStockDto) {
        return mvtStockService.correctionStockNegative(mvtStockDto);
    }
}
