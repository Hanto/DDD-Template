package com.ddd.context.application.ports;// Created by jhant on 11/06/2022.

public interface EventListener<T extends Event>
{
    void onApplicationEvent(T domainEvent);
}