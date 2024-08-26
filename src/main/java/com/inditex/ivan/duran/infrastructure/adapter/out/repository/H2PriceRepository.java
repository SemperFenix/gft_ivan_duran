package com.inditex.ivan.duran.infrastructure.adapter.out.repository;

import com.inditex.ivan.duran.application.port.out.PriceRepository;
import com.inditex.ivan.duran.domain.model.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class H2PriceRepository implements PriceRepository {

    private final PricesJpaRepository pricesJpaRepository;

    public H2PriceRepository(PricesJpaRepository pricesJpaRepository){
        this.pricesJpaRepository = pricesJpaRepository;
    }

    @Override
    public List<Price> getPricesAtDateTime(LocalDateTime startDate, int brandId, int productId) {
        return pricesJpaRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrand_CodigoAndProductId(startDate, startDate, brandId, productId);
    }
}
