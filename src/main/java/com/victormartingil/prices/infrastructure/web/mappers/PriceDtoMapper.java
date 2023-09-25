package com.victormartingil.prices.infrastructure.web.mappers;

import com.victormartingil.prices.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.PriceResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {

    @Mapping(target = "startDate", expression = "java(dateToString(source.getStartDate()))")
    @Mapping(target = "endDate", expression = "java(dateToString(source.getEndDate()))")
    PriceResponseDto toPriceResponseDto(Price source);

    default String dateToString(final LocalDateTime source) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        return source.format(formatter);
    }

}
