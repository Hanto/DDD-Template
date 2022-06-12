package com.ddd.context.domain.querybus;// Created by jhant on 12/06/2022.

public interface QueryBus
{
    <T> T handle(Query<T> query);
}