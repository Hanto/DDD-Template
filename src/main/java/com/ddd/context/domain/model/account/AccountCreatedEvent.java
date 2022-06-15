package com.ddd.context.domain.model.account;// Created by jhant on 14/06/2022.

import com.ddd.context.application.ports.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter @ToString(callSuper = true)
public class AccountCreatedEvent extends Event
{
    private Long accountId;
    private String clientId;
    private BigDecimal balance;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public AccountCreatedEvent(Long accountId, String aggregateType, int version, String clientId, BigDecimal balance)
    {
        super(accountId, aggregateType, version);
        this.accountId = accountId;
        this.clientId = clientId;
        this.balance = balance;
    }
}