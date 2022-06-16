package com.ddd.context.infraestructure.api.controllers.product;// Created by jhant on 04/06/2022.

import com.ddd.context.domain.model.product.ProductProyection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDTOMapper
{
    ProductDTO fromModel(ProductProyection model)
    {
        return ProductDTO.builder()
            .productId(model.getProductId().getId())
            .shortName(model.getProductName().getShortName())
            .longName(model.getProductName().getLongName())
            .build();
    }
}