package com.ddd.context.application.ports;// Created by jhant on 12/06/2022.

public interface CommandBus
{
    <T extends Command> void send(T command);
}