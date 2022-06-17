package com.ddd.context.domain.repositories;// Created by jhant on 10/06/2022.

import com.ddd.context.domain.common.Event;

public interface OutboxRepository
{
    void saveNewEvent(Event event);
}