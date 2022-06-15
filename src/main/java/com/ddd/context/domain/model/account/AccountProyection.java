package com.ddd.context.domain.model.account;// Created by jhant on 15/06/2022.

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) @ToString
public class AccountProyection
{
    @EqualsAndHashCode.Include
    @Getter private AccountId accountId;
    @Getter private String clientId;
    @Getter private BigDecimal balance;
    @Getter private int version;

    // EVENT HANDLERS::
    //--------------------------------------------------------------------------------------------------------

    public void apply(AccountCreatedEvent event)
    {
        this.accountId = new AccountId(event.getAccountId());
        this.clientId = event.getClientId();
        this.balance = event.getBalance();
        this.version = event.getVersion();
    }

    public void apply(MoneyDepositedEvent event)
    {
        this.balance = event.getBalance();
        this.version = event.getVersion();
    }
}