package com.victormartingil.prices.infrastructure.web.controllers;

import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.domain.ports.PriceService;
import com.victormartingil.prices.infrastructure.web.exceptions.functional.FunctionalErrorCode;
import com.victormartingil.prices.infrastructure.web.exceptions.functional.FunctionalException;
import com.victormartingil.prices.infrastructure.web.mappers.PriceDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.ApiApi;
import org.openapitools.model.PriceRequestDto;
import org.openapitools.model.PriceResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PriceController implements ApiApi {

    private final PriceService priceService;

    private final PriceDtoMapper priceDtoMapper;

    @Override
    public ResponseEntity<PriceResponseDto> getPrice(final Integer productId, final Integer brandId, final String date) {
        log.info("[PriceController] - getPrice - productId: {}, brandId: {}, date: {}", productId, brandId, date);

        final PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setProductId(productId);
        priceRequestDto.setBrandId(brandId);
        priceRequestDto.setDate(date);

        final Price price = priceService.getPrice(priceRequestDto)
                .orElseThrow(() -> new FunctionalException(FunctionalErrorCode.PRICE_NOT_FOUND));

        final PriceResponseDto response = priceDtoMapper.toPriceResponseDto(price);
        log.info("[PriceController] - getPrice - PriceResponseDto: {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
