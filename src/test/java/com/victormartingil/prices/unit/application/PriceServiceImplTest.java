package com.victormartingil.prices.unit.application;

import com.victormartingil.prices.application.PriceServiceImpl;
import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.domain.ports.PriceRepositoryPort;
import com.victormartingil.prices.utils.PriceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceRequestDto;

import java.util.Optional;

import static com.victormartingil.prices.utils.PriceUtils.createPrice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void getPrice() {
        // Given
        final PriceRequestDto priceRequestDto = PriceUtils.createAnyPriceRequestDto();
        final Price price = createPrice();

        // When
        when(priceRepositoryPort.getPrice(priceRequestDto)).thenReturn(Optional.of(price));

        final Optional<Price> result = priceService.getPrice(priceRequestDto);

        // Then
        assertThat(result).isPresent();
        assertEquals(result.get(), price);
    }


}