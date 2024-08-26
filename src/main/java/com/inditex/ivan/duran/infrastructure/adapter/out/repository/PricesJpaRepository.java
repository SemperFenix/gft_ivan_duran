package com.inditex.ivan.duran.infrastructure.adapter.out.repository;

import com.inditex.ivan.duran.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesJpaRepository extends JpaRepository<Price, Long> {
    List<Price> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrand_CodigoAndProductId(LocalDateTime startDate, LocalDateTime endDate, int brandId, int productId);
}
