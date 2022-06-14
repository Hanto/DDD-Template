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
    @NonNull private String aggregateType;
    @NonNull private LocalDateTime occurredOn;
    @NonNull private Integer version;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DomainEvent(@NonNull String aggregateId, @NonNull String aggregateType, @NonNull Integer version)
    {
        this.eventId        = new DomainEventId(UUID.randomUUID().toString());
        this.type           = this.getClass().getSimpleName();
        this.aggregateId    = aggregateId;
        this.aggregateType  = aggregateType;
        this.occurredOn     = LocalDateTime.now(ZoneId.of("GMT"));
        this.version        = version;
    }

    public DomainEvent(@NonNull String aggregateId, String aggregateType)
    {   this(aggregateId, aggregateType,0); }
}