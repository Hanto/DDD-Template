package com.ddd.context.infraestructure.api;// Created by jhant on 07/06/2022.

import com.ddd.context.application.commands.account.CreateAccountCommand;
import com.ddd.context.application.commands.account.DepositMoneyCommand;
import com.ddd.context.domain.commands.CommandBus;
import com.ddd.context.infraestructure.api.dtos.ApiDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController @RequestMapping(value ="/api", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@RequiredArgsConstructor @SuppressWarnings("all")
@Log4j2
public class RootController
{
    // ROOT: (discovery)
    //--------------------------------------------------------------------------------------------------------

    @GetMapping("")
    public ApiDTO getIndex() throws NoSuchMethodException
    {
        ApiDTO api = new ApiDTO();

        api.add(linkTo(methodOn(RootController.class).getIndex()).withSelfRel());

        return api;
    }

    @Autowired private CommandBus commandBus;
    @GetMapping("/create/{id}")
    public void test2(@PathVariable String id)
    {
        CreateAccountCommand command = new CreateAccountCommand(UUID.randomUUID());
        commandBus.send(command);
    }

    @GetMapping("/deposit/{id}/{amount}")
    public void test3(@PathVariable String id, @PathVariable float amount)
    {
        DepositMoneyCommand command = new DepositMoneyCommand(UUID.fromString(id), new BigDecimal(amount));
        commandBus.send(command);
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {}
}