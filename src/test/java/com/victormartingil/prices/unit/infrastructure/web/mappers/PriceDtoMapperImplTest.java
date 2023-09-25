package com.victormartingil.prices.unit.infrastructure.web.mappers;

import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.infrastructure.web.mappers.PriceDtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceResponseDto;

import java.time.format.DateTimeFormatter;

import static com.victormartingil.prices.utils.PriceUtils.createPrice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PriceDtoMapperImplTest {

    @InjectMocks
    private PriceDtoMapperImpl priceDtoMapper;

    @Test
    void priceToPriceResponseDto() {
        // Given
        final Price price = createPrice();

        // When
        final PriceResponseDto response = priceDtoMapper.toPriceResponseDto(price);

        // Then
        assertThat(response).isNotNull();
        assertEqualsPrices(price, response);
    }

    @Test
    void itReturnsNullWhenPriceEntityIsNull() {
        // Given

        // When
        final PriceResponseDto response = priceDtoMapper.toPriceResponseDto(null);

        // Then
        assertNull(response);
    }

    private void assertEqualsPrices(final Price source, final PriceResponseDto response) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        final String startDate = formatter.format(source.getStartDate());
        final String endDate = formatter.format(source.getEndDate());

        assertEquals(source.getId(), response.getId());
        assertEquals(source.getBrandId(), response.getBrandId());
        assertEquals(source.getProductId(), response.getProductId());
        assertEquals(source.getPriceList(), response.getPriceList());
        assertEquals(source.getPrice(), response.getPrice());
        assertEquals(startDate, response.getStartDate());
        assertEquals(endDate, response.getEndDate());
    }

}