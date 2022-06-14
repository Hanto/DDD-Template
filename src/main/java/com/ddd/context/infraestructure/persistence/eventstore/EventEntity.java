package com.ddd.context.infraestructure.persistence.eventstore;// Created by jhant on 14/06/2022.

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity @DynamicInsert @DynamicUpdate
@Table(name = "EVENT_STORE", indexes =
{
    @Index(name = "aggregateIdIndex", columnList = "AGGREGATE_ID"),
})
@NoArgsConstructor @AllArgsConstructor @Builder
@Setter @Getter @ToString
public class EventEntity implements Persistable<String>
{
    // ENTITY:
    //--------------------------------------------------------------------------------------------------------

    @Id
    @Column(name = "EVENT_ID", nullable = false)
    @Setter(AccessLevel.NONE)
    private String eventId;

    @Column(name = "AGGREGATE_ID", nullable = false)
    @NotNull
    private String aggregateId;

    @Column(name = "VERSION", nullable = false)
    @NotNull
    private int version;

    @Column(name = "EVENT_TYPE", nullable = false)
    @NotNull
    private String eventType;

    @Column(name = "OCCURED_ON", nullable = false)
    @NotNull
    private LocalDateTime occurredOn;

    @Column(name = "PAYLOAD", nullable = false) @Lob
    @NotNull
    private String payload;

    // PERSISTABLE (for fast inserts):
    //--------------------------------------------------------------------------------------------------------

    @Override
    public String getId()
    {   return eventId; }

    @Transient
    private boolean isNew = true;
}
