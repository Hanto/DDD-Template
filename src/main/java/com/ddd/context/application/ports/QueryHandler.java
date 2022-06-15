package com.ddd.context.application.ports;// Created by jhant on 12/06/2022.

public interface QueryHandler<T extends Query<R>, R>
{
    R handle(T query);
}