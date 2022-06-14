package com.ddd.context.domain.events;// Created by jhant on 10/06/2022.

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) @ToString
public abstract class DomainEvent implements Serializable
{
    @EqualsAndHashCode.Include
    @NonNull private DomainEventId eventId;
    @NonNull private String type; // change the ObjectSerializer if the field changes its name
    @NonNull private String aggregateId;
    @NonNull private LocalDateTime occurredOn;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DomainEvent(@NonNull String aggregateId, Class<?>eventClass)
    {
        this.eventId    = new DomainEventId(UUID.randomUUID().toString());
        this.type       = eventClass.getSimpleName();
        this.aggregateId= aggregateId;
        this.occurredOn = LocalDateTime.now(ZoneId.of("GMT"));
    }
}