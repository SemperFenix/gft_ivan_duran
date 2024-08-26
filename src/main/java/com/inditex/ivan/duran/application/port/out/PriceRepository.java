package com.inditex.ivan.duran.application.port.out;

import com.inditex.ivan.duran.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> getPricesAtDateTime(LocalDateTime startDate, int brandId, int productId);
}
