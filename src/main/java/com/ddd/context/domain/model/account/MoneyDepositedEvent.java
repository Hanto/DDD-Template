package com.ddd.context.domain.model.account;// Created by jhant on 14/06/2022.

import com.ddd.context.application.ports.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter @ToString(callSuper = true)
public class MoneyDepositedEvent extends Event
{
    private Long accountId;
    private BigDecimal amount;
    private BigDecimal balance;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public MoneyDepositedEvent(Long accountId, String aggregateType, int version, BigDecimal amount, BigDecimal balance)
    {
        super(accountId, aggregateType, version);
        this.accountId = accountId;
        this.amount = amount;
        this.balance = balance;
    }
}