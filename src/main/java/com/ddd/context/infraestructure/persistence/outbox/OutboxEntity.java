package com.ddd.context.infraestructure.persistence.outbox;// Created by jhant on 10/06/2022.

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity @DynamicInsert @DynamicUpdate @Table(name = "OUTBOX")
@NoArgsConstructor @AllArgsConstructor @Builder
@Setter @Getter @ToString
public class OutboxEntity implements Persistable<String>
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

    @Column(name = "AGGREGATE_TYPE", nullable = false)
    @NotNull
    private String aggregateType;

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
    private boolean isNew = false;
}