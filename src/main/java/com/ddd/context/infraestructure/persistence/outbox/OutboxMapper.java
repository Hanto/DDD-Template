package com.ddd.context.infraestructure.persistence.outbox;// Created by jhant on 10/06/2022.

import com.ddd.context.domain.common.Event;
import com.ddd.context.infraestructure.common.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor
public class OutboxMapper
{
    @Autowired private final ObjectSerializer serializer;

    // FROM DOMAIN:
    //--------------------------------------------------------------------------------------------------------

    public OutboxEntity fromDomain(Event event)
    {
        return OutboxEntity.builder()
            .eventId(event.getEventId().getId())
            .aggregateId(event.getAggregateId())
            .aggregateType(event.getAggregateType())
            .eventType(event.getType())
            .occurredOn(event.getOccurredOn())
            .payload(serializer.toJson(event))
            .build();
    }

    // FROM ENTITY:
    //--------------------------------------------------------------------------------------------------------

    public Event fromEntity(OutboxEntity entity)
    {   return serializer.fromEventJson(entity.getPayload(), entity.getEventType()); }
}