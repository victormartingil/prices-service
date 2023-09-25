package com.victormartingil.prices.application;

import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.domain.ports.PriceRepositoryPort;
import com.victormartingil.prices.domain.ports.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.PriceRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    /**
     * This method is used to get the price from the repository.
     *
     * @param priceRequestDto the price request dto.
     * @return the price.
     */

    @Override
    public Optional<Price> getPrice(final PriceRequestDto priceRequestDto) {
        log.info("[PriceServiceImpl] - getPrice - priceRequestDto: {}", priceRequestDto);
        return priceRepositoryPort.getPrice(priceRequestDto);
    }
}
