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
public class AccountCreatedEvent extends Event
{
    private AccountId accountId;
    private String clientId;
    private BigDecimal balance;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public AccountCreatedEvent(AccountId accountId, int version, String clientId, BigDecimal balance)
    {
        super(accountId.getId(), Account.class.getSimpleName(), version);
        this.accountId = accountId;
        this.clientId = clientId;
        this.balance = balance;
    }
}