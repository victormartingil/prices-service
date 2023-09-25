package com.victormartingil.prices.infrastructure.persistence.mappers;

import com.victormartingil.prices.domain.model.Price;
import com.victormartingil.prices.infrastructure.persistence.entities.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    public Price toPrice(PriceEntity source);

}
