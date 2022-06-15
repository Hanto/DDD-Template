package com.ddd.context.infraestructure.api.dtos;// Created by jhant on 15/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.domain.model.account.AccountProyection;
import com.ddd.context.infraestructure.api.RootController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringComponent @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor
public class AccountDTOAssembler implements RepresentationModelAssembler<AccountProyection, AccountDTO>
{
    @Autowired private final AccountDTOMapper mapper;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public @NonNull AccountDTO toModel(@NonNull AccountProyection entity)
    {
        AccountDTO account = mapper.fromModel(entity);

        Link selfLink = linkTo(methodOn(RootController.class)
            .getAccount(account.getAccountId()))
            .withSelfRel();

        account.add(selfLink);
        return account;
    }

}