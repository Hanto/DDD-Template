package com.ddd.context.infraestructure.api.product;// Created by jhant on 04/06/2022.

import com.ddd.context.domain.model.product.Price;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class PriceDTOAssembler implements RepresentationModelAssembler<Price, PriceDTO>
{
    @Autowired private final PriceDTOMapper mapper;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public @NonNull PriceDTO toModel(@NonNull Price price)
    {   return mapper.fromModel(price); }
}