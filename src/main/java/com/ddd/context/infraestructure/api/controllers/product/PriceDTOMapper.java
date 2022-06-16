package com.ddd.context.infraestructure.api.controllers.product;// Created by jhant on 04/06/2022.

import com.ddd.context.domain.model.product.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceDTOMapper
{
    PriceDTO fromModel(Price model)
    {
        return PriceDTO.builder()
            .priceId(model.getPriceId().getId())
            .brandId(model.getBrandId().getId())
            .startDate(model.getDateInterval().getStartDate())
            .endDate(model.getDateInterval().getEndDate())
            .priority(model.getPriority())
            .currency(model.getMoney().getCurrency().name())
            .price(model.getMoney().getCuantity())
            .build();
    }
}
