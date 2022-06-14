package com.ddd.context.domain.events;// Created by jhant on 12/06/2022.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor @AllArgsConstructor
@Getter
public class DomainEventId
{
    @NonNull String id;
}