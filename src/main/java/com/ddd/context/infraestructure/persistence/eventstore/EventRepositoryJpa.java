package com.ddd.context.infraestructure.persistence.eventstore;// Created by jhant on 14/06/2022.

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryJpa extends JpaRepository<EventEntity, String>
{
    Optional<Version> findFirstByAggregateIdOrderByVersionDesc(String aggregateId);
    List<EventEntity> findByAggregateId(String aggregateId);

    interface Version
    {   int getVersion(); }
}