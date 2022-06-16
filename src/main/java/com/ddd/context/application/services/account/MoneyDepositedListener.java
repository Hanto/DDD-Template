package com.ddd.context.application.services.account;// Created by jhant on 14/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.common.EventListener;
import com.ddd.context.domain.model.account.AccountProyection;
import com.ddd.context.domain.model.account.events.MoneyDepositedEvent;
import com.ddd.context.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@RequiredArgsConstructor
public class MoneyDepositedListener implements EventListener<MoneyDepositedEvent>
{
    @Autowired private final AccountRepository adapter;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void onApplicationEvent(MoneyDepositedEvent event)
    {
        AccountProyection account = adapter.loadAccount(event.getAccountId());

        account.apply(event);

        adapter.save(account);
    }
}