package com.ddd.context.domain.repositories;// Created by jhant on 14/06/2022.

import com.ddd.context.application.common.Event;
import com.ddd.context.domain.common.DomainAggregateRoot;

import java.util.List;

public interface EventRepository
{
    void saveAggregateRoot(DomainAggregateRoot aggregate);
    List<Event> loadEvents(String aggregateId, String aggregateType);
}