package com.ddd.context.domain.model.account;// Created by jhant on 14/06/2022.

import com.ddd.context.application.ports.Event;
import com.ddd.context.domain.model.DomainAggregateRoot;
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
        AccountCreatedEvent event = new AccountCreatedEvent(accountId.getId(), getType(), getNextVersion(), clientId, BigDecimal.ZERO);

        applyNewEvent(event);
    }

    public Account(List<Event> events)
    {   super(events); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public String getId()
    {   return accountId.getId().toString(); }

    public void deposit(BigDecimal amount)
    {
        BigDecimal newBalance = balance.add(amount);

        MoneyDepositedEvent event = new MoneyDepositedEvent(accountId.getId(), getType(), getNextVersion(), amount, newBalance);

        applyNewEvent(event);
    }

    // EVENT HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    private void apply(AccountCreatedEvent event)
    {
        this.accountId = new AccountId(event.getAccountId());
        this.clientId = event.getClientId();
        this.balance = event.getBalance();
    }

    private void apply(MoneyDepositedEvent event)
    {   this.balance = event.getBalance(); }
}