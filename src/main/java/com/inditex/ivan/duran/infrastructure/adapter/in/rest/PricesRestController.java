package com.inditex.ivan.duran.infrastructure.adapter.in.rest;


import com.inditex.ivan.duran.application.port.in.PriceService;
import com.inditex.ivan.duran.domain.model.Price;
import com.inditex.ivan.duran.infrastructure.adapter.in.rest.dto.PriceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/prices")
public class PricesRestController {
    private static final Logger LOG = Logger.getLogger("PricesRestController");
    private final PriceService priceService;

    public PricesRestController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "Get the price for a specific date and time")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the price",
                                        content = {@Content(mediaType = "application/json",
                                                            schema = @Schema(implementation = PriceDTO.class))}),
                            @ApiResponse(responseCode = "204", description = "Price not found",
                                                content = @Content)})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDTO> getPricesForDate(@Parameter(name = "startDate", description = "Date and time for the price to request", example= "2020-06-14-00.00.00", required = true)
                                                     @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime startDate,

                                                     @Parameter(name = "productId", description = "Id of the product", example = "1", required = true)
                                                     @RequestParam("productId") int productId,

                                                     @Parameter(name = "brandId", description = "Id of the brand", example = "1", required = true)
                                                     @RequestParam("brandId") int brandId) {

        Optional<PriceDTO> priceToReturn = getPriceDTO(startDate, productId, brandId);
        return priceToReturn.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.noContent().build());
    }

    private Optional<PriceDTO> getPriceDTO(LocalDateTime startDate, int productId, int brandId) {
        List<Price> pricesInDateRange = priceService.getPricesAtDateTime(startDate, brandId, productId);

        Optional<Price> priorityPrice = pricesInDateRange.stream().max(Comparator.comparingInt(Price::getPriority));
        priorityPrice.ifPresentOrElse(price -> LOG.info(String.format("Price found >%n%s", price)),
                                         () -> LOG.info("No prices found for those values"));
        return priorityPrice.map(PriceDTO::getPriceDTO);
    }
}
