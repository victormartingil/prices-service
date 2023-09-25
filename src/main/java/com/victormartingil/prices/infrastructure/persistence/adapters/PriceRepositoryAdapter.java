package com.victormartingil.prices.infrastructure.persistence.adapters;

import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.domain.ports.PriceRepositoryPort;
import com.victormartingil.prices.infrastructure.persistence.entities.PriceEntity;
import com.victormartingil.prices.infrastructure.persistence.mappers.PriceEntityMapper;
import com.victormartingil.prices.infrastructure.persistence.repositories.PriceH2JpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.PriceRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceH2JpaRepository priceH2JpaRepository;
    private final PriceEntityMapper priceEntityMapper;

    /**
     * This method is used to get the price from the repository.
     *
     * @param priceRequestDto the price request dto.
     * @return the price.
     */

    public Optional<Price> getPrice(final PriceRequestDto priceRequestDto) {
        log.info("[PriceRepositoryAdapter] - getPrice - priceRequestDto: {}", priceRequestDto);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        final LocalDateTime date = LocalDateTime.parse(priceRequestDto.getDate(), formatter);

        final Optional<PriceEntity> highestPriorityPrice = priceH2JpaRepository
                .findTopByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        priceRequestDto.getBrandId(),
                        priceRequestDto.getProductId(),
                        date,
                        date);

        return highestPriorityPrice.map(priceEntityMapper::toPrice);
    }

}
