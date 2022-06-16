package com.ddd.context.application.common;// Created by jhant on 12/06/2022.

public interface CommandBus
{
    <T extends Command> void send(T command);
}