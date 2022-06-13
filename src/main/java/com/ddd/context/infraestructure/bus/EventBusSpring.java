package com.ddd.context.infraestructure.bus;// Created by jhant on 10/06/2022.

import com.ddd.context.domain.events.DomainEvent;
import com.ddd.context.domain.events.DomainEventListener;
import com.ddd.context.domain.events.EventBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component @Scope(SCOPE_SINGLETON) @Primary
@RequiredArgsConstructor @Log4j2
public class EventBusSpring implements EventBus
{
    private final ApplicationEventPublisher publisher;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    @Autowired
    public EventBusSpring(ApplicationEventPublisher publisher, ApplicationEventMulticaster eventMulticaster,
        List<DomainEventListener<?>> listeners)
    {
        this.publisher = publisher;
        createEventListeners(eventMulticaster, listeners);
    }

    // PUBLISH:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void publish(Collection<DomainEvent> events)
    {   events.forEach(publisher::publishEvent); }

    // CREATE LISTENERS:
    //--------------------------------------------------------------------------------------------------------

    private void createEventListeners( ApplicationEventMulticaster eventMulticaster, List<DomainEventListener<?>>listeners)
    {
        for (DomainEventListener<?> listener: listeners)
            eventMulticaster.addApplicationListener(createListener(listener));
    }

    private <T extends DomainEvent> ApplicationListener<PayloadApplicationEvent<T>> createListener(DomainEventListener<T> listener)
    {
        log.info("Creating application listener for {}", listener.getClass().getSimpleName());
        return event -> listener.onApplicationEvent(event.getPayload());
    }
}