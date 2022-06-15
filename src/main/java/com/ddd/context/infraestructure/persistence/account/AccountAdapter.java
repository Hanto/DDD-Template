package com.ddd.context.infraestructure.persistence.account;// Created by jhant on 14/06/2022.

import com.ddd.context.domain.model.account.AccountProyection;
import com.ddd.context.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component @Scope(SCOPE_SINGLETON) @Primary
@RequiredArgsConstructor
public class AccountAdapter implements AccountRepository
{
    @Autowired private final AccountMapper mapper;
    @Autowired private final AccountRepositoryJpa accountRepository;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void save(AccountProyection account)
    {   accountRepository.save(mapper.fromDomain(account)); }

    @Override
    public AccountProyection getAccount(Long accountId)
    {
        AccountEntity entity = accountRepository.findById(accountId)
            .orElseThrow(() -> new EntityNotFoundException(format("No account exists with the id: %s", accountId)));

        return mapper.fromEntity(entity);
    }
}