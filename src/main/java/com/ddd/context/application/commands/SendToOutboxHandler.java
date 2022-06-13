package com.ddd.context.application.commands;// Created by jhant on 12/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.domain.commands.CommandHandler;
import com.ddd.context.domain.repositories.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SpringComponent
@RequiredArgsConstructor
public class SendToOutboxHandler implements CommandHandler<SendToOutboxCommand>
{
    @Autowired private final OutboxRepository outboxRepository;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void handle(SendToOutboxCommand command)
    {   outboxRepository.saveNewEvent(command.getEvent()); }
}