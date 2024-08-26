package com.inditex.ivan.duran.infrastructure.adapter.in.rest;

import com.inditex.ivan.duran.infrastructure.adapter.in.rest.dto.PriceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricesRestControllerIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void first_proposed_test() {
        webTestClient.get().uri("/prices?startDate=2020-06-14-10.00.00&productId=35455&brandId=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceDTO.class)
                .value(priceDTO -> {
                    assertEquals(35455, priceDTO.productId());
                    assertEquals(1, priceDTO.brandId());
                    assertEquals(1, priceDTO.priceRate());
                    assertEquals("2020-06-14-00.00.00", priceDTO.startDate());
                    assertEquals("2020-12-31-23.59.59", priceDTO.endDate());
                    assertEquals("35.50 EUR", priceDTO.priceToApply());
                });
    }

    @Test
    void second_proposed_test() {
        webTestClient.get().uri("/prices?startDate=2020-06-14-16.00.00&productId=35455&brandId=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceDTO.class)
                .value(priceDTO -> {
                    assertEquals(35455, priceDTO.productId());
                    assertEquals(1, priceDTO.brandId());
                    assertEquals(2, priceDTO.priceRate());
                    assertEquals("2020-06-14-15.00.00", priceDTO.startDate());
                    assertEquals("2020-06-14-18.30.00", priceDTO.endDate());
                    assertEquals("25.45 EUR", priceDTO.priceToApply());
                });
    }

    @Test
    void third_proposed_test() {
        webTestClient.get().uri("/prices?startDate=2020-06-14-21.00.00&productId=35455&brandId=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceDTO.class)
                .value(priceDTO -> {
                    assertEquals(35455, priceDTO.productId());
                    assertEquals(1, priceDTO.brandId());
                    assertEquals(1, priceDTO.priceRate());
                    assertEquals("2020-06-14-00.00.00", priceDTO.startDate());
                    assertEquals("2020-12-31-23.59.59", priceDTO.endDate());
                    assertEquals("35.50 EUR", priceDTO.priceToApply());
                });
    }

    @Test
    void fourth_proposed_test() {
        webTestClient.get().uri("/prices?startDate=2020-06-15-10.00.00&productId=35455&brandId=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceDTO.class)
                .value(priceDTO -> {
                    assertEquals(35455, priceDTO.productId());
                    assertEquals(1, priceDTO.brandId());
                    assertEquals(3, priceDTO.priceRate());
                    assertEquals("2020-06-15-00.00.00", priceDTO.startDate());
                    assertEquals("2020-06-15-11.00.00", priceDTO.endDate());
                    assertEquals("30.50 EUR", priceDTO.priceToApply());
                });
    }

    @Test
    void fifth_proposed_test() {
        webTestClient.get().uri("/prices?startDate=2020-06-16-21.00.00&productId=35455&brandId=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceDTO.class)
                .value(priceDTO -> {
                    assertEquals(35455, priceDTO.productId());
                    assertEquals(1, priceDTO.brandId());
                    assertEquals(4, priceDTO.priceRate());
                    assertEquals("2020-06-15-16.00.00", priceDTO.startDate());
                    assertEquals("2020-12-31-23.59.59", priceDTO.endDate());
                    assertEquals("38.95 EUR", priceDTO.priceToApply());
                });
    }

    @Test
    void price_not_found() {
        webTestClient.get().uri("/prices?startDate=2020-06-16-21.00.00&productId=35455&brandId=2")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void error_endpoint_test(){
        webTestClient.get().uri("/test")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .isEqualTo("Error 404 - Recurso no encontrado");
    }
}
