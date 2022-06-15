package com.ddd.context.application.account;// Created by jhant on 14/06/2022.

import com.ddd.context.application.ports.Query;
import com.ddd.context.application.ports.SelfValidatingObject;
import com.ddd.context.domain.model.account.AccountId;
import com.ddd.context.domain.model.account.AccountProyection;
import com.ddd.context.infraestructure.persistence.account.AccountEntity;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value @EqualsAndHashCode(callSuper = false)
public class FindAccountQuery extends SelfValidatingObject<AccountEntity> implements Query<AccountProyection>
{
    @NotNull AccountId accountId;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public FindAccountQuery(Long accountId)
    {
        this.accountId = new AccountId(accountId);
        this.validateSelf();
    }
}