package com.ddd.context.infraestructure.persistence.account;// Created by jhant on 15/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.domain.model.account.AccountId;
import com.ddd.context.domain.model.account.AccountProyection;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@SpringComponent @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor
public class AccountMapper
{
    // FROM DOMAIN:
    //--------------------------------------------------------------------------------------------------------

    public AccountEntity fromDomain(AccountProyection account)
    {
        return AccountEntity.builder()
            .accountId(account.getAccountId().getId())
            .clientId(account.getClientId())
            .balance(account.getBalance())
            .version(account.getVersion())
            .build();
    }

    // FROM ENTITY:
    //--------------------------------------------------------------------------------------------------------

    public AccountProyection fromEntity(AccountEntity entity)
    {
        return AccountProyection.builder()
            .accountId(new AccountId(entity.getAccountId()))
            .clientId(entity.getClientId())
            .balance(entity.getBalance())
            .version(entity.getVersion())
            .build();
    }
}
