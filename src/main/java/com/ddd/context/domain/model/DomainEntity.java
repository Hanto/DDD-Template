package com.ddd.context.domain.model;// Created by jhant on 10/06/2022.

import com.ddd.context.domain.events.DomainEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class DomainEntity
{
    private List<DomainEvent>domainEvents = new ArrayList<>();

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    final public List<DomainEvent>pullDomainEvents()
    {
        List<DomainEvent>events = domainEvents;
        domainEvents = Collections.emptyList();
        return events;
    }

    final protected void recordEvent(DomainEvent event)
    {   domainEvents.add(event); }

    final protected void recordEvents(Collection<DomainEvent> events)
    {   domainEvents.addAll(events); }
}