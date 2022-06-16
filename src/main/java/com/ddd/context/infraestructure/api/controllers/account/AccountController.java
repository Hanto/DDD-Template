package com.ddd.context.infraestructure.api.controllers.account;// Created by jhant on 15/06/2022.

import com.ddd.context.application.common.CommandBus;
import com.ddd.context.application.common.QueryBus;
import com.ddd.context.application.services.account.CreateAccountCommand;
import com.ddd.context.application.services.account.DepositMoneyCommand;
import com.ddd.context.application.services.account.FindAccountQuery;
import com.ddd.context.domain.model.account.AccountProyection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value ="/api", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@RequiredArgsConstructor @SuppressWarnings("all")
public class AccountController
{
    @Autowired private final AccountDTOAssembler accountAssembler;
    @Autowired private final CommandBus commandBus;
    @Autowired private final QueryBus queryBus;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @PostMapping("/account/{id}")
    public void createAccount(@PathVariable Long id)
    {
        CreateAccountCommand command = new CreateAccountCommand(id, "Random client");
        commandBus.send(command);
    }

    @GetMapping("/account/{id}")
    public AccountDTO getAccount(@PathVariable Long id)
    {
        FindAccountQuery query = new FindAccountQuery(id);
        AccountProyection account =  queryBus.send(query);
        return accountAssembler.toModel(account);
    }

    @PostMapping("/account/deposit/{id}/{amount}")
    public void depositMoney(@PathVariable Long id, @PathVariable Float amount)
    {
        DepositMoneyCommand command = new DepositMoneyCommand(id, new BigDecimal(amount));
        commandBus.send(command);
    }
}