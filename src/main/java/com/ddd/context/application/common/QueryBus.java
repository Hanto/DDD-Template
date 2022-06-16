package com.ddd.context.application.common;// Created by jhant on 12/06/2022.

public interface QueryBus
{
    <T> T send(Query<T> query);
}