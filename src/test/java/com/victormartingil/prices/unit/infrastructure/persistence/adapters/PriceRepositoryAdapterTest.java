package com.victormartingil.prices.unit.infrastructure.persistence.adapters;

import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.infrastructure.persistence.adapters.PriceRepositoryAdapter;
import com.victormartingil.prices.infrastructure.persistence.entities.PriceEntity;
import com.victormartingil.prices.infrastructure.persistence.mappers.PriceEntityMapper;
import com.victormartingil.prices.infrastructure.persistence.repositories.PriceH2JpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceRequestDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.victormartingil.prices.utils.PriceUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    PriceH2JpaRepository priceH2JpaRepository;

    @Mock
    PriceEntityMapper priceEntityMapper;

    @InjectMocks
    PriceRepositoryAdapter priceRepositoryAdapter;

    @Test
    void getPrice() {
        // Given
        final PriceRequestDto priceRequestDto = createAnyPriceRequestDto();
        final PriceEntity priceEntity = createPriceEntity();
        final Price price = createPrice();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        final LocalDateTime date = LocalDateTime.parse(priceRequestDto.getDate(), formatter);

        // When
        when(priceH2JpaRepository.findTopByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                priceRequestDto.getBrandId(),
                priceRequestDto.getProductId(),
                date,
                date))
                .thenReturn(Optional.of(priceEntity));
        when(priceEntityMapper.toPrice(priceEntity)).thenReturn(price);

        final Optional<Price> result = priceRepositoryAdapter.getPrice(priceRequestDto);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getBrandId()).isEqualTo(priceRequestDto.getBrandId());
        assertThat(result.get().getProductId()).isEqualTo(priceRequestDto.getProductId());
    }

}