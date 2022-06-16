package com.ddd.context.domain.repositories;// Created by jhant on 14/06/2022.

import com.ddd.context.domain.model.account.AccountId;
import com.ddd.context.domain.model.account.AccountProyection;

public interface AccountRepository
{
    void save(AccountProyection event);
    AccountProyection loadAccount(AccountId accountId);
}