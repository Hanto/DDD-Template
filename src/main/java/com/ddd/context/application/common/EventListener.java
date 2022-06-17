package com.ddd.context.application.common;// Created by jhant on 11/06/2022.

import com.ddd.context.domain.common.Event;

public interface EventListener<T extends Event>
{
    void onApplicationEvent(T domainEvent);
}