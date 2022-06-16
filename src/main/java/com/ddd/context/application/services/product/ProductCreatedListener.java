package com.ddd.context.application.services.product;// Created by jhant on 16/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.common.EventListener;
import com.ddd.context.domain.model.product.ProductProyection;
import com.ddd.context.domain.model.product.events.ProductCreatedEvent;
import com.ddd.context.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@RequiredArgsConstructor
public class ProductCreatedListener implements EventListener<ProductCreatedEvent>
{
    @Autowired private final ProductRepository adapter;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void onApplicationEvent(ProductCreatedEvent event)
    {
        ProductProyection product = new ProductProyection();

        product.apply(event);

        adapter.saveProduct(product);
    }
}