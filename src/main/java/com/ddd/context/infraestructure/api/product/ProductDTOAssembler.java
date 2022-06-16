package com.ddd.context.infraestructure.api.product;// Created by jhant on 04/06/2022.

import com.ddd.context.domain.model.product.ProductProyection;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component @RequiredArgsConstructor
public class ProductDTOAssembler implements RepresentationModelAssembler<ProductProyection, ProductDTO>
{
    @Autowired private final ProductDTOMapper mapper;
    @Autowired private final PriceDTOAssembler priceDTOAssembler;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public @NonNull ProductDTO toModel(@NonNull ProductProyection entity)
    {
        ProductDTO productDTO = mapper.fromModel(entity);
        List<PriceDTO> priceDTOs = entity.getPriceList().stream()
            .map(priceDTOAssembler::toModel)
            .toList();

        productDTO.setPrices(priceDTOs);

        Link selfLink = linkTo(methodOn(ProductController.class)
            .getProduct(productDTO.getProductId()))
            .withSelfRel();

        productDTO.add(selfLink);

        return productDTO;
    }
}