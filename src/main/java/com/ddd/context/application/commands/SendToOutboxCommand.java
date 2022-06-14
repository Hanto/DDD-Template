package com.ddd.context.application.commands;// Created by jhant on 12/06/2022.

import com.ddd.context.application.SelfValidatingObject;
import com.ddd.context.domain.events.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value @EqualsAndHashCode(callSuper = false)
public class SendToOutboxCommand extends SelfValidatingObject<SendToOutboxCommand>
{
    @NotNull DomainEvent event;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public SendToOutboxCommand(DomainEvent event)
    {
        this.event = event;
        this.validateSelf();
    }
}