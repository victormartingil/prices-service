package com.victormartingil.prices.domain.ports;

import com.victormartingil.prices.domain.model.Price;
import org.openapitools.model.PriceRequestDto;

import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<Price> getPrice(PriceRequestDto priceRequestDto);

}
