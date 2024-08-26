package com.inditex.ivan.duran.application.usecase;

import com.inditex.ivan.duran.application.port.in.PriceService;
import com.inditex.ivan.duran.application.port.out.PriceRepository;
import com.inditex.ivan.duran.domain.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getPricesAtDateTime(LocalDateTime startDate, int brandId, int productId) {
        return priceRepository.getPricesAtDateTime(startDate, brandId, productId);
    }


}
