package fr.gestiondestock.services;

import fr.gestiondestock.dto.MvtStockDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStockService {

    BigDecimal stockReelArticle(Integer idArticle);

    List<MvtStockDto> mvtStockArticle(Integer idArticle);

    MvtStockDto entreeStockArticle(MvtStockDto mvtStockDto);

    MvtStockDto sortieStockArticle(MvtStockDto mvtStockDto);

    MvtStockDto correctionStockPositive(MvtStockDto mvtStockDto);

    MvtStockDto correctionStockNegative(MvtStockDto mvtStockDto);

}
