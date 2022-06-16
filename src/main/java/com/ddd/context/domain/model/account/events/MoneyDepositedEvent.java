package com.ddd.context.domain.model.account.events;// Created by jhant on 14/06/2022.

import com.ddd.context.application.ports.Event;
import com.ddd.context.domain.model.account.Account;
import com.ddd.context.domain.model.account.AccountId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter @ToString(callSuper = true)
public class MoneyDepositedEvent extends Event
{
    private AccountId accountId;
    private BigDecimal amount;
    private BigDecimal balance;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public MoneyDepositedEvent(AccountId accountId, int version, BigDecimal amount, BigDecimal balance)
    {
        super(accountId.getId(), Account.class.getSimpleName(), version);
        this.accountId = accountId;
        this.amount = amount;
        this.balance = balance;
    }
}