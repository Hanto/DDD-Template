package com.ddd.context.infraestructure.persistence.eventstore;// Created by jhant on 14/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.domain.events.DomainEvent;
import com.ddd.context.infraestructure.common.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@SpringComponent @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor
public class EventMapper
{
    @Autowired private final ObjectSerializer serializer;

    // FROM DOMAIN:
    //--------------------------------------------------------------------------------------------------------

    public EventEntity fromDomain(DomainEvent event)
    {
        return EventEntity.builder()
            .eventId(event.getEventId().getId())
            .aggregateId(event.getAggregateId())
            .version(event.getVersion())
            .eventType(event.getType())
            .occurredOn(event.getOccurredOn())
            .payload(serializer.toJson(event))
            .build();
    }

    // FROM ENTITY:
    //--------------------------------------------------------------------------------------------------------

    public DomainEvent fromEntity(EventEntity entity)
    {   return serializer.fromJson(entity.getPayload(), DomainEvent.class.getPackageName(), entity.getEventType()); }
}