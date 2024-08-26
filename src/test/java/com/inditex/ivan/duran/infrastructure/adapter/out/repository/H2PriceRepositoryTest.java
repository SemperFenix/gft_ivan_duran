package com.inditex.ivan.duran.infrastructure.adapter.out.repository;

import com.inditex.ivan.duran.domain.model.Brand;
import com.inditex.ivan.duran.domain.model.Price;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@DataJpaTest
@Transactional
class H2PriceRepositoryTest {
    public static final LocalDateTime MOCK_START_DATE = LocalDateTime.of(2024, 8, 24, 20, 20);
    public static final LocalDateTime MOCK_END_DATE = LocalDateTime.of(2024, 8, 25, 15, 10);

    @MockBean
    private PricesJpaRepository price;
    private H2PriceRepository priceRepository;

    @BeforeEach
    void setUp(){
        priceRepository = new H2PriceRepository(price);

    }

    @Test
    void testGetNoPrices() {
        when(price.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrand_CodigoAndProductId(any(LocalDateTime.class), any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        List<Price> prices = priceRepository.getPricesAtDateTime(LocalDateTime.now(), 1, 1);
        assertTrue(prices.isEmpty());
    }

    @Test
    void testGetPricesAtDateTime() {
        Brand testBrand = new Brand();
        testBrand.setCodigo(1L);
        testBrand.setNombre("TEST");
        Price testPrice = new Price();
        testPrice.setBrand(testBrand);
        testPrice.setStartDate(MOCK_START_DATE);
        testPrice.setEndDate(MOCK_END_DATE);
        testPrice.setPriceRate(99);
        testPrice.setProductId(100);
        testPrice.setPriority(105);
        testPrice.setPriceToApply(9.99);
        testPrice.setCurrency("TES");
        when(price.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrand_CodigoAndProductId(any(LocalDateTime.class), any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(List.of(testPrice));
        List<Price> prices = priceRepository.getPricesAtDateTime(MOCK_START_DATE, 1, 100);
        assertEquals(1, prices.size());
        assertEquals(MOCK_START_DATE, testPrice.getStartDate());
        assertEquals(MOCK_END_DATE, testPrice.getEndDate());
        assertEquals(100, prices.get(0).getProductId());
        assertEquals(99, prices.get(0).getPriceRate());
        assertEquals(105, prices.get(0).getPriority());
        assertEquals(9.99, prices.get(0).getPriceToApply());
        assertEquals("TES", prices.get(0).getCurrency());
    }
}
