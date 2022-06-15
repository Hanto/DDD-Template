package com.ddd.context.infraestructure.persistence.account;// Created by jhant on 14/06/2022.

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositoryJpa extends JpaRepository<AccountEntity, Long>
{
}
