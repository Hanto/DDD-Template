package com.ddd.context.domain.events;// Created by jhant on 11/06/2022.

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class ExampleEvent extends DomainEvent
{
    private String stringField;
    private Integer integerField;
    private Boolean booleanField;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public ExampleEvent(Long aggregateId, String stringField, Integer integerField, Boolean booleanField)
    {
        super(aggregateId, ExampleEvent.class);
        this.stringField = stringField;
        this.integerField = integerField;
        this.booleanField = booleanField;
    }
}