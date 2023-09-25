package com.victormartingil.prices.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.domain.ports.PriceService;
import com.victormartingil.prices.infrastructure.web.controllers.PriceController;
import com.victormartingil.prices.infrastructure.web.mappers.PriceDtoMapper;
import com.victormartingil.prices.utils.PriceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.model.PriceRequestDto;
import org.openapitools.model.PriceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static com.victormartingil.prices.utils.PriceUtils.createAnyPriceRequestDto;
import static com.victormartingil.prices.utils.PriceUtils.createPrice;
import static org.mockito.Mockito.when;

@WebMvcTest(PriceController.class)
@AutoConfigureMockMvc
class PriceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PriceService priceService;

    @MockBean
    private PriceDtoMapper priceDtoMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetPriceWithValidRequest() throws Exception {
        // Given
        final PriceRequestDto requestDto = createAnyPriceRequestDto();
        final Price price = createPrice();
        final PriceResponseDto responseDto = PriceUtils.createAnyPriceResponseDto();

        // When
        when(priceService.getPrice(requestDto)).thenReturn(Optional.of(price));
        when(priceDtoMapper.toPriceResponseDto(price)).thenReturn(responseDto);

        // Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/price")
                        .param("productId", requestDto.getProductId().toString())
                        .param("brandId", requestDto.getBrandId().toString())
                        .param("date", requestDto.getDate())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void testGetPriceWithNotFound() throws Exception {
        // Given
        final PriceRequestDto requestDto = createAnyPriceRequestDto();

        // When
        when(priceService.getPrice(requestDto)).thenReturn(Optional.empty());

        // Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/price")
                        .param("productId", requestDto.getProductId().toString())
                        .param("brandId", requestDto.getBrandId().toString())
                        .param("date", requestDto.getDate())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}