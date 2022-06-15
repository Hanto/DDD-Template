package com.ddd.context.application.outbox;// Created by jhant on 12/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.ports.Event;
import com.ddd.context.application.ports.EventListener;
import com.ddd.context.domain.repositories.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@RequiredArgsConstructor
public class EventCreatedListener implements EventListener<Event>
{
    @Autowired private final OutboxRepository outboxRepository;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void onApplicationEvent(Event event)
    {   outboxRepository.saveNewEvent(event); }
}