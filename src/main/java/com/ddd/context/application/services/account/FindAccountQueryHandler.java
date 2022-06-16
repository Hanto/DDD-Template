package com.ddd.context.application.services.account;// Created by jhant on 14/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.common.QueryHandler;
import com.ddd.context.domain.model.account.AccountProyection;
import com.ddd.context.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SpringComponent
@RequiredArgsConstructor
public class FindAccountQueryHandler implements QueryHandler<FindAccountQuery, AccountProyection>
{
    @Autowired private final AccountRepository accountRepository;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public AccountProyection handle(FindAccountQuery query)
    {   return accountRepository.loadAccount(query.getAccountId()); }
}