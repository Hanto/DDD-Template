package com.ddd.context.application.ports;// Created by jhant on 12/06/2022.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor @AllArgsConstructor
@Getter
public class EventId
{
    @NonNull String id;
}