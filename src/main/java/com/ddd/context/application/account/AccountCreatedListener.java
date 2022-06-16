package com.ddd.context.application.account;// Created by jhant on 14/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.ports.EventListener;
import com.ddd.context.domain.model.account.AccountProyection;
import com.ddd.context.domain.model.account.events.AccountCreatedEvent;
import com.ddd.context.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@RequiredArgsConstructor
public class AccountCreatedListener implements EventListener<AccountCreatedEvent>
{
    @Autowired private final AccountRepository adapter;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void onApplicationEvent(AccountCreatedEvent event)
    {
        AccountProyection account = new AccountProyection();

        account.apply(event);

        adapter.save(account);
    }
}