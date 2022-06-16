package com.ddd.context.infraestructure.api.account;// Created by jhant on 15/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.domain.model.account.AccountProyection;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@SpringComponent @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor
public class AccountDTOMapper
{
    AccountDTO fromModel(AccountProyection model)
    {
        return AccountDTO.builder()
            .accountId(model.getAccountId().getId())
            .clientId(model.getClientId())
            .balance(model.getBalance())
            .version(model.getVersion())
            .build();
    }
}