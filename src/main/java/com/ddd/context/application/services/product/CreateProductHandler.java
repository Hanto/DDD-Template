package com.ddd.context.application.services.product;// Created by jhant on 16/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.common.CommandHandler;
import com.ddd.context.application.common.EventBus;
import com.ddd.context.domain.model.product.Product;
import com.ddd.context.domain.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SpringComponent
@RequiredArgsConstructor
public class CreateProductHandler implements CommandHandler<CreateProductCommand>
{
    @Autowired private final EventRepository eventRepository;
    @Autowired private final EventBus eventBus;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void handle(CreateProductCommand command)
    {
        Product product = new Product(command.getProductId(), command.getProductName());

        eventRepository.saveAggregateRoot(product);
        eventBus.publish(product.getNewEvents());
    }
}
