package com.ddd.context.infraestructure.persistence.eventstore;// Created by jhant on 14/06/2022.

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryJpa extends JpaRepository<EventEntity, String>
{
    Optional<Version> findFirstByAggregateIdAndAggregateTypeOrderByVersionDesc(String aggregateId, String AggregateType);
    List<EventEntity> findByAggregateIdAndAggregateType(String aggregateId, String aggregateType);

    interface Version
    {   int getVersion(); }
}