package com.ddd.context.application.product;// Created by jhant on 16/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.ports.CommandHandler;
import com.ddd.context.application.ports.Event;
import com.ddd.context.application.ports.EventBus;
import com.ddd.context.domain.model.product.Product;
import com.ddd.context.domain.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@RequiredArgsConstructor
public class AddPriceCommandHandler implements CommandHandler<AddPriceCommand>
{
    @Autowired private final EventRepository eventRepository;
    @Autowired private final EventBus eventBus;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void handle(AddPriceCommand command)
    {
        List<Event> events = eventRepository.loadEvents(command.getProductId().getId().toString(), Product.class.getSimpleName());
        Product product = new Product(events);

        product.addPrice(command.getPriceId(), command.getBrandId(), command.getDateInterval(), command.getPriority(), command.getMoney());

        eventRepository.saveAggregateRoot(product);
        eventBus.publish(product.getNewEvents());
    }
}