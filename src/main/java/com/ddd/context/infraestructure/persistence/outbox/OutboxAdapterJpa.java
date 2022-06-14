package com.ddd.context.infraestructure.persistence.outbox;// Created by jhant on 10/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.domain.events.DomainEvent;
import com.ddd.context.domain.repositories.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@SpringComponent @Scope(SCOPE_SINGLETON) @Primary
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