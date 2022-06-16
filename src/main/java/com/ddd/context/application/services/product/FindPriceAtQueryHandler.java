package com.ddd.context.application.services.product;// Created by jhant on 16/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.common.QueryHandler;
import com.ddd.context.domain.model.product.Price;
import com.ddd.context.domain.model.product.ProductProyection;
import com.ddd.context.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@RequiredArgsConstructor
public class FindPriceAtQueryHandler implements QueryHandler<FindPriceAtQuery, Price>
{
    @Autowired private final ProductRepository productRepository;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public Price handle(FindPriceAtQuery query)
    {
        ProductProyection proyection = productRepository.loadProduct(query.getProductId());
        return proyection.getPriceAt(query.getDateTime(), query.getBrandId());
    }
}
