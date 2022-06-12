package com.ddd.context.adapter.bus;// Created by jhant on 10/06/2022.

import com.ddd.context.domain.events.DomainEventListener;
import com.ddd.context.domain.events.EventBus;
import com.ddd.context.domain.events.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component @Primary @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor @Log4j2
public class EventBusSpring implements EventBus
{
    @Autowired private final ApplicationEventPublisher publisher;
    @Autowired private final ApplicationEventMulticaster eventMulticaster;
    @Autowired private final ApplicationContext applicationContext;

    // LOAD LISTENERS:
    //--------------------------------------------------------------------------------------------------------

    @PostConstruct
    public void createListeners()
    {   findAndLoadEventListeners(); }

    // PUBLISH:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void publish(Collection<DomainEvent> events)
    {   events.forEach(publisher::publishEvent); }

    // LISTEN:
    //--------------------------------------------------------------------------------------------------------

    @SuppressWarnings("rawtypes")
    private void findAndLoadEventListeners()
    {
        List<DomainEventListener> listeners = applicationContext.getBeansOfType(DomainEventListener.class).values().stream().toList();

        for (DomainEventListener<?> listener: listeners)
            eventMulticaster.addApplicationListener(createListener(listener));
    }

    private <T extends DomainEvent> ApplicationListener<PayloadApplicationEvent<T>> createListener(DomainEventListener<T> listener)
    {
        log.info("Creating application listener for {}", listener.getClass().getSimpleName());
        return event -> listener.onApplicationEvent(event.getPayload());
    }
}