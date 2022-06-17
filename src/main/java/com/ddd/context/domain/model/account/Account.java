package com.ddd.context.domain.model.account;// Created by jhant on 14/06/2022.

import com.ddd.context.domain.common.DomainAggregateRoot;
import com.ddd.context.domain.common.Event;
import com.ddd.context.domain.model.account.events.AccountCreatedEvent;
import com.ddd.context.domain.model.account.events.MoneyDepositedEvent;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) @ToString
public class Account extends DomainAggregateRoot
{
    @EqualsAndHashCode.Include
    private AccountId accountId;
    private String clientId;
    private BigDecimal balance;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public Account(AccountId accountId, String clientId)
    {
        AccountCreatedEvent event = new AccountCreatedEvent(accountId, getNextVersion(), clientId, BigDecimal.ZERO);

        applyNewEvent(event);
    }

    public Account(List<Event> events)
    {   applyAllEvents(events); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void deposit(BigDecimal amount)
    {
        BigDecimal newBalance = balance.add(amount);

        MoneyDepositedEvent event = new MoneyDepositedEvent(accountId, getNextVersion(), amount, newBalance);

        applyNewEvent(event);
    }

    @Override
    public String getId()
    {   return accountId.getId().toString(); }

    // EVENT HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    private void apply(AccountCreatedEvent event)
    {
        this.accountId = event.getAccountId();
        this.clientId = event.getClientId();
        this.balance = event.getBalance();
    }

    private void apply(MoneyDepositedEvent event)
    {   this.balance = event.getBalance(); }
}