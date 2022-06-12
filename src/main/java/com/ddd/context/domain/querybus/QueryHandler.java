package com.ddd.context.domain.querybus;// Created by jhant on 12/06/2022.

public interface QueryHandler<R, T extends Query<R>>
{
    R handle(T query);
}