package com.ddd.context.domain.model;// Created by jhant on 11/06/2022.

import com.ddd.context.domain.events.DomainEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DomainAggregate extends DomainEntity
{
    public abstract List<DomainEvent>pullAllDomainEvents();

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @SafeVarargs
    protected final <T extends DomainEntity> void pullEventsFrom(T... entities)
    {   pullEventsFrom(Arrays.asList(entities)); }

    protected <T extends DomainEntity>void pullEventsFrom(Collection<T> entities)
    {
        Collection<DomainEvent> pulledEvents = entities.stream()
            .map(domainEntity -> domainEntity instanceof DomainAggregate ?
                ((DomainAggregate) domainEntity).pullAllDomainEvents() :
                domainEntity.pullDomainEvents())
            .flatMap(Collection::stream).collect(Collectors.toList());

        recordEvents(pulledEvents);
    }
}