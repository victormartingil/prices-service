package com.victormartingil.prices.unit.infrastructure.persistence.mappers;

import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.infrastructure.persistence.entities.PriceEntity;
import com.victormartingil.prices.infrastructure.persistence.mappers.PriceEntityMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.victormartingil.prices.utils.PriceUtils.createPriceEntity;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceEntityMapperImplTest {

    @InjectMocks
    private PriceEntityMapperImpl priceEntityMapper;

    @Test
    void priceEntityToPrice() {
        // Given
        final PriceEntity priceEntity = createPriceEntity();

        // When
        final Price result = priceEntityMapper.toPrice(priceEntity);

        // Then
        assertNotNull(result);
        assertEqualsPrices(priceEntity, result);

    }

    @Test
    void itReturnsNullWhenPriceEntityIsNull() {
        // Given

        // When
        final Price result = priceEntityMapper.toPrice(null);

        // Then
        assertNull(result);
    }

    private void assertEqualsPrices(final PriceEntity source, final Price response) {
        assertEquals(source.getId(), response.getId());
        assertEquals(source.getBrandId(), response.getBrandId());
        assertEquals(source.getProductId(), response.getProductId());
        assertEquals(source.getPriceList(), response.getPriceList());
        assertEquals(source.getPrice(), response.getPrice());
        assertEquals(source.getStartDate(), response.getStartDate());
        assertEquals(source.getEndDate(), response.getEndDate());
    }

}