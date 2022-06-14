package com.ddd.context.domain.model;// Created by jhant on 13/06/2022.

import com.ddd.context.domain.events.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;

@NoArgsConstructor
public abstract class DomainAggregateRootES
{
    @Getter private int baseVersion = 0;
    private final List<DomainEvent> newEvents = new ArrayList<>();

    private final static String EVENT_METHOD_PREFIX = "apply";

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    protected DomainAggregateRootES(@NonNull List<DomainEvent>events)
    {
        List<DomainEvent> orderedEvents = orderEvents(events);
        orderedEvents.forEach( event ->
        {
            apply(event);
            this.baseVersion = event.getVersion();
        });
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    public String getType()
    {   return this.getClass().getSimpleName(); }

    protected int getNextVersion()
    {   return baseVersion + newEvents.size() + 1; }

    public abstract String getId();

    // APPLY EVENTS :
    //--------------------------------------------------------------------------------------------------------

    final protected void applyNewEvent(DomainEvent event)
    {
        if (event.getVersion() != getNextVersion())
            throw new IllegalArgumentException(format("New event version: %s doesn't match expected new version: %s",
                event.getVersion(), getNextVersion()));

        apply(event);
        recordNewEvent(event);
    }

    private void apply(DomainEvent event)
    {
        try
        {
            Method method = this.getClass().getDeclaredMethod(EVENT_METHOD_PREFIX, event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        }
        catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e)
        {
            throw new UnsupportedOperationException(
            format("Aggregate '%s' doesn't apply event type '%s'", this.getClass(), event.getClass()), e);
        }
    }

    // RECORD EVENTS:
    //--------------------------------------------------------------------------------------------------------

    final protected void recordNewEvent(DomainEvent event)
    {   newEvents.add(event); }

    final public List<DomainEvent> getNewEvents()
    {   return Collections.unmodifiableList(newEvents); }



    private List<DomainEvent>orderEvents(List<DomainEvent>unorderedEvents)
    {   return unorderedEvents.stream().sorted(Comparator.comparing(DomainEvent::getVersion)).toList(); }
}