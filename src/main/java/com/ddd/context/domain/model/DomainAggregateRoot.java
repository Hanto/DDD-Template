package com.ddd.context.domain.model;// Created by jhant on 11/06/2022.

import com.ddd.context.domain.events.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DomainAggregateRoot extends DomainAggregate
{
    private List<DomainEvent> domainEvents = new ArrayList<>();

    // EVENTS:
    //--------------------------------------------------------------------------------------------------------

    final protected void recordEvent(DomainEvent event)
    {   domainEvents.add(event); }

    final public List<DomainEvent> pullEvents()
    {
        List<DomainEvent>events = domainEvents;
        domainEvents = Collections.emptyList();
        return events;
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    public String getType()
    {   return this.getClass().getSimpleName(); }
}