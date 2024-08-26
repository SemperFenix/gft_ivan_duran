package com.inditex.ivan.duran.infrastructure.adapter.in.rest;

import com.inditex.ivan.duran.application.port.in.PriceService;
import com.inditex.ivan.duran.domain.model.Brand;
import com.inditex.ivan.duran.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PricesRestController.class)
class PricesRestControllerTest {

    public static final LocalDateTime MOCK_START_DATE = LocalDateTime.of(2024, 8, 24, 20, 20);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PriceService priceService;

    @Test
    void get_Price_OK() throws Exception {
        Brand testBrand = new Brand();
        testBrand.setCodigo(1L);
        testBrand.setNombre("TEST");
        Price testPrice = new Price();
        testPrice.setBrand(testBrand);
        testPrice.setStartDate(MOCK_START_DATE);
        testPrice.setEndDate(LocalDateTime.now());
        testPrice.setPriceRate(99);
        testPrice.setProductId(100);
        testPrice.setPriority(105);
        testPrice.setPriceToApply(9.99);
        testPrice.setCurrency("TES");


        when(priceService.getPricesAtDateTime(any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(List.of(testPrice));

        mockMvc.perform(get("/prices?startDate=2020-06-14-16.00.00&productId=35455&brandId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("100"))
                .andExpect(jsonPath("$.brandId").value("1"))
                .andExpect(jsonPath("$.priceRate").value("99"))
                .andExpect(jsonPath("$.startDate").value("2024-08-24-20.20.00"))
                .andExpect(jsonPath("$.priceToApply").value("9.99 TES"));
    }

    @Test
    void get_No_Price() throws Exception {
        when(priceService.getPricesAtDateTime(any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/prices?startDate=2020-06-14-16.00.00&productId=35455&brandId=1"))
                .andExpect(status().isNoContent());
    }
}
