package com.inditex.ivan.duran.application.port.in;

import com.inditex.ivan.duran.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {
    List<Price> getPricesAtDateTime(LocalDateTime startDate, int brandId, int productId);
}
