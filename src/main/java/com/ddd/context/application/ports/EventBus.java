package com.ddd.context.application.ports;// Created by jhant on 10/06/2022.

import java.util.Collection;

public interface EventBus
{
    void publish(final Collection<Event>events);
}