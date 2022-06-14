package com.ddd.context.infraestructure.persistence.eventstore;// Created by jhant on 14/06/2022.

import com.ddd.context.domain.events.DomainEvent;
import com.ddd.context.domain.model.DomainAggregateRootES;
import com.ddd.context.domain.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void saveAggregateRoot(DomainAggregateRootES aggregate)
    {
        Optional<Integer> savedVersion = getCurrentStoredVersion(aggregate);

        if (savedVersion.isPresent() && savedVersion.get() != aggregate.getBaseVersion())
            throw new OptimisticLockException("Base version doesn't match stored version");

        List<EventEntity>entities = aggregate.getNewEvents().stream().map(mapper::fromDomain).toList();
        entities.forEach(entity -> entity.setNew(true));

        eventRepository.saveAll(entities);
    }

    @Override
    public List<DomainEvent> loadEvents(UUID aggregateId)
    {
        List<EventEntity>entities = eventRepository.findByAggregateId(aggregateId.toString());

        return entities.stream().map(mapper::fromEntity).toList();
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private Optional<Integer>getCurrentStoredVersion(DomainAggregateRootES aggregate)
    {
        return eventRepository.findFirstByAggregateIdOrderByVersionDesc(aggregate.getId().toString())
            .map(EventRepositoryJpa.Version::getVersion);
    }
}