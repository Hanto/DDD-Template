package com.ddd.context.application.ports;// Created by jhant on 12/06/2022.

public interface CommandHandler<T extends Command>
{
    void handle(T command);
}