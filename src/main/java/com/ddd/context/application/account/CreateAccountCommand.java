package com.ddd.context.application.account;// Created by jhant on 14/06/2022.

import com.ddd.context.application.ports.Command;
import com.ddd.context.application.ports.SelfValidatingObject;
import com.ddd.context.domain.model.account.AccountId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value @EqualsAndHashCode(callSuper = false)
public class CreateAccountCommand extends SelfValidatingObject<CreateAccountCommand> implements Command
{
    @NotNull AccountId accountId;
    @NotNull String clientId;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public CreateAccountCommand(Long accountId, String clientId)
    {
        this.accountId = new AccountId(accountId);
        this.clientId = clientId;
        this.validateSelf();
    }
}