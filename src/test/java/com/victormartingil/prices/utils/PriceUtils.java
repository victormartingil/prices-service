package com.victormartingil.prices.utils;

import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.infrastructure.persistence.entities.PriceEntity;
import io.cucumber.datatable.DataTable;
import org.openapitools.model.PriceRequestDto;
import org.openapitools.model.PriceResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PriceUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    public static PriceRequestDto createPriceRequestDto(final String productId, final String brandId, final String date) {
        final PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setBrandId(Integer.parseInt(brandId));
        priceRequestDto.setProductId(Integer.parseInt(productId));
        priceRequestDto.setDate(date);
        return priceRequestDto;
    }

    public static PriceRequestDto createAnyPriceRequestDto() {
        return createPriceRequestDto("1", "1", "2020-06-14-10.00.00");
    }

    public static PriceResponseDto createAnyPriceResponseDto() {
        return new PriceResponseDto()
                .id(1L)
                .brandId(1)
                .productId(1)
                .priceList(1)
                .startDate("2020-06-14-00.00.00")
                .endDate("2020-12-31-23.59.59")
                .price(35.50);
    }

    public static Price createPrice() {
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-00.00.00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23.59.59", formatter);

        return Price.builder()
                .brandId(1)
                .productId(1)
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price(35.50)
                .curr("EUR")
                .build();
    }

    public static PriceEntity createPriceEntity() {
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-00.00.00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23.59.59", formatter);

        return PriceEntity.builder()
                .id(1L)
                .brandId(1)
                .productId(1)
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price(35.50)
                .curr("EUR")
                .priority(0)
                .build();
    }

    public static PriceResponseDto createPriceResponseDtoFromDateTable(final DataTable dataTable) {
        final Map<String, String> dataTableMap = dataTable.asMap(String.class, String.class);
        return createPriceResponseDtoFromString(
                dataTableMap.get("Product ID"),
                dataTableMap.get("Brand ID"),
                dataTableMap.get("Price List"),
                dataTableMap.get("Start Date"),
                dataTableMap.get("End Date"),
                dataTableMap.get("Price"));
    }

    private static PriceResponseDto createPriceResponseDtoFromString(
            final String productId,
            final String brandId,
            final String priceList,
            final String startDate,
            final String endDate,
            final String price) {
        final PriceResponseDto priceResponseDto = new PriceResponseDto();
        priceResponseDto.setId(1L);
        priceResponseDto.setProductId(Integer.parseInt(productId));
        priceResponseDto.setBrandId(Integer.parseInt(brandId));
        priceResponseDto.setPriceList(Integer.parseInt(priceList));
        priceResponseDto.setStartDate(startDate);
        priceResponseDto.setEndDate(endDate);
        priceResponseDto.setPrice(Double.parseDouble(price));
        return priceResponseDto;
    }

}
