package com.ddd.context.application.account;// Created by jhant on 14/06/2022.

import com.ddd.context.application.ports.Command;
import com.ddd.context.application.ports.SelfValidatingObject;
import com.ddd.context.domain.model.account.AccountId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value @EqualsAndHashCode(callSuper = false)
public class DepositMoneyCommand extends SelfValidatingObject<DepositMoneyCommand> implements Command
{
    @NotNull AccountId accountId;
    @NotNull @Min(0) BigDecimal money;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public DepositMoneyCommand(Long accountId, BigDecimal money)
    {
        this.accountId = new AccountId(accountId);
        this.money = money;
        this.validateSelf();
    }
}