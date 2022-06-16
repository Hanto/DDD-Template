package com.ddd.context.infraestructure.persistence.eventstore;// Created by jhant on 14/06/2022.

import com.ddd.context.application.ports.Event;
import com.ddd.context.domain.common.DomainAggregateRoot;
import com.ddd.context.domain.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component @Scope(SCOPE_SINGLETON) @Primary
@RequiredArgsConstructor
public class EventAdapterJpa implements EventRepository
{
    @Autowired private final EventRepositoryJpa eventRepository;
    @Autowired private final EventMapper mapper;

    // EVENT:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void saveAggregateRoot(DomainAggregateRoot aggregate)
    {
        Optional<Integer> savedVersion = getCurrentStoredVersion(aggregate.getId(), aggregate.getType());

        if (savedVersion.isPresent() && savedVersion.get() != aggregate.getBaseVersion())
            throw new OptimisticLockException("Base version doesn't match stored version");

        List<EventEntity>entities = aggregate.getNewEvents().stream().map(mapper::fromDomain).toList();
        entities.forEach(entity -> entity.setNew(true));

        eventRepository.saveAll(entities);
    }

    @Override
    public List<Event> loadEvents(String aggregateId, String aggregateType)
    {
        List<EventEntity>entities = eventRepository.findByAggregateIdAndAggregateType(aggregateId, aggregateType);

        if (entities.isEmpty())
            throw new EntityNotFoundException(format("Entity not found %s", aggregateId));

        return entities.stream().map(mapper::fromEntity).toList();
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private Optional<Integer>getCurrentStoredVersion(String aggregateId, String aggregateType)
    {
        return eventRepository.findFirstByAggregateIdAndAggregateTypeOrderByVersionDesc(aggregateId, aggregateType)
            .map(EventRepositoryJpa.Version::getVersion);
    }
}