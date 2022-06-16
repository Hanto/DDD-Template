package com.ddd.context.application.services.account;// Created by jhant on 14/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.common.CommandHandler;
import com.ddd.context.application.common.Event;
import com.ddd.context.application.common.EventBus;
import com.ddd.context.domain.model.account.Account;
import com.ddd.context.domain.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringComponent
@RequiredArgsConstructor
public class DepositMoneyHandler implements CommandHandler<DepositMoneyCommand>
{
    @Autowired private final EventRepository eventRepository;
    @Autowired private final EventBus eventBus;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void handle(DepositMoneyCommand command)
    {
        List<Event> events = eventRepository.loadEvents(command.getAccountId().getId().toString(), Account.class.getSimpleName());
        Account account = new Account(events);

        account.deposit(command.getMoney());

        eventRepository.saveAggregateRoot(account);
        eventBus.publish(account.getNewEvents());
    }
}