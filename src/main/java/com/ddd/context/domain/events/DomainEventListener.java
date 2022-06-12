package com.ddd.context.domain.events;// Created by jhant on 11/06/2022.

public interface DomainEventListener<T extends DomainEvent>
{
    void onApplicationEvent(T domainEvent);
}