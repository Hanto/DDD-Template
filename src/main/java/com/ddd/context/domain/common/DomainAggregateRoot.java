package com.ddd.context.domain.common;// Created by jhant on 13/06/2022.

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
public abstract class DomainAggregateRoot
{
    @Getter private int baseVersion = 0;
    private final List<Event> newEvents = new ArrayList<>();

    private final static String EVENT_METHOD_PREFIX = "apply";

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    public String getType()
    {   return this.getClass().getSimpleName(); }

    protected int getNextVersion()
    {   return baseVersion + newEvents.size() + 1; }

    public abstract String getId();

    // APPLY EVENTS :
    //--------------------------------------------------------------------------------------------------------

    protected void applyAllEvents(@NonNull List<Event>events)
    {
        List<Event> orderedEvents = orderEvents(events);
        orderedEvents.forEach( event ->
        {
            apply(event);
            this.baseVersion = event.getVersion();
        });
    }

    final protected void applyNewEvent(Event event)
    {
        if (event.getVersion() != getNextVersion())
            throw new IllegalArgumentException(format("New event version: %s doesn't match expected new version: %s",
                event.getVersion(), getNextVersion()));

        apply(event);
        recordNewEvent(event);
    }

    private void apply(Event event)
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

    final protected void recordNewEvent(Event event)
    {   newEvents.add(event); }

    final public List<Event> getNewEvents()
    {   return Collections.unmodifiableList(newEvents); }



    private List<Event>orderEvents(List<Event>unorderedEvents)
    {   return unorderedEvents.stream().sorted(Comparator.comparing(Event::getVersion)).toList(); }
}