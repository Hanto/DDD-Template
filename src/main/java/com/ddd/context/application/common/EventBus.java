package com.ddd.context.application.common;// Created by jhant on 10/06/2022.

import com.ddd.context.domain.common.Event;

import java.util.Collection;

public interface EventBus
{
    void publish(final Collection<Event>events);
}