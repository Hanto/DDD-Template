package com.ddd.context.application.services.product;// Created by jhant on 16/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.common.EventListener;
import com.ddd.context.domain.model.product.ProductProyection;
import com.ddd.context.domain.model.product.events.PriceAddedEvent;
import com.ddd.context.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@RequiredArgsConstructor
public class PriceAddedListener implements EventListener<PriceAddedEvent>
{
    @Autowired private final ProductRepository adapter;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public void onApplicationEvent(PriceAddedEvent event)
    {
        ProductProyection product = adapter.loadProduct(event.getProductId());

        product.apply(event);

        adapter.saveProduct(product);
    }
}