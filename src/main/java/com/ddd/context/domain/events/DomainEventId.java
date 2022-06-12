package com.ddd.context.domain.events;// Created by jhant on 12/06/2022.

import lombok.NonNull;
import lombok.Value;

@Value
public class DomainEventId
{
    @NonNull String id;
}