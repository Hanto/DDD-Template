package com.ddd.context.application.services.account;// Created by jhant on 14/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.common.CommandHandler;
import com.ddd.context.application.common.EventBus;
import com.ddd.context.domain.model.account.Account;
import com.ddd.context.domain.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SpringComponent
@RequiredArgsConstructor
public class CreateAccountHandler implements CommandHandler<CreateAccountCommand>
{
    @Autowired private final EventRepository eventRepository;
    @Autowired private final EventBus eventBus;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void handle(CreateAccountCommand command)
    {
        Account account = new Account(command.getAccountId(), command.getClientId());

        eventRepository.saveAggregateRoot(account);
        eventBus.publish(account.getNewEvents());
    }
}