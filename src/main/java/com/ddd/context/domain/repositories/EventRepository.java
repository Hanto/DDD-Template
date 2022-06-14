package com.ddd.context.domain.repositories;// Created by jhant on 14/06/2022.

import com.ddd.context.domain.events.DomainEvent;
import com.ddd.context.domain.model.DomainAggregateRootES;

import java.util.List;
import java.util.UUID;

public interface EventRepository
{
    void saveAggregateRoot(DomainAggregateRootES aggregate);
    List<DomainEvent> loadEvents(UUID aggregateId);
}