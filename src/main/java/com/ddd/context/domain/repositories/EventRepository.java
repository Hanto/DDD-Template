package com.ddd.context.domain.repositories;// Created by jhant on 14/06/2022.

import com.ddd.context.domain.events.DomainEvent;
import com.ddd.context.domain.model.DomainAggregateRootES;

import java.util.List;

public interface EventRepository
{
    void saveAggregateRoot(DomainAggregateRootES aggregate);
    List<DomainEvent> loadEvents(String aggregateId, String aggregateType);
}