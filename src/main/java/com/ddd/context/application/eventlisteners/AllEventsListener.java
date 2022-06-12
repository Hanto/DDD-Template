package com.ddd.context.application.eventlisteners;// Created by jhant on 12/06/2022.

import com.ddd.common.shared.annotations.SpringComponent;
import com.ddd.context.application.commands.SendToOutboxCommand;
import com.ddd.context.domain.commands.CommandBus;
import com.ddd.context.domain.events.DomainEvent;
import com.ddd.context.domain.events.DomainEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@RequiredArgsConstructor @Log4j2
public class AllEventsListener implements DomainEventListener<DomainEvent>
{
    @Autowired private final CommandBus commandBus;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void onApplicationEvent(DomainEvent domainEvent)
    {
        SendToOutboxCommand sendToOutbox = new SendToOutboxCommand(domainEvent);
        commandBus.send(sendToOutbox);
    }
}