package com.ddd.context.adapter.persistence.outbox;// Created by jhant on 10/06/2022.

import com.ddd.context.domain.repositories.OutboxRepository;
import com.ddd.context.domain.events.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component @Primary @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor
public class OutboxAdapterJpa implements OutboxRepository
{
    @Autowired private final OutboxRepositoryJpa eventRepository;
    @Autowired private final OutboxMapper eventMapper;

    // EVENT:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void saveNewEvent(DomainEvent event)
    {
        OutboxEntity entity = eventMapper.fromDomain(event);
        entity.setNew(true);
        eventRepository.save(entity);
    }
}