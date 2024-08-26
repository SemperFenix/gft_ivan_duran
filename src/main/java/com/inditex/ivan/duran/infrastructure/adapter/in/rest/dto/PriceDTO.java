package com.inditex.ivan.duran.infrastructure.adapter.in.rest.dto;

import com.inditex.ivan.duran.domain.model.Price;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record PriceDTO(int productId,
                       Long brandId,
                       int priceRate,
                       String startDate,
                       String endDate,
                       String priceToApply) {

    public static PriceDTO getPriceDTO(Price price) {

        return new PriceDTO(price.getProductId(),
                            price.getBrand().getCodigo(),
                            price.getPriceRate(),
                            price.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")),
                            price.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")),
                            String.format(Locale.ENGLISH,"%.2f %s", price.getPriceToApply(), price.getCurrency()));
    }


}
