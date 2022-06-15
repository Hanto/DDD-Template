package com.ddd.context.infraestructure.common;// Created by jhant on 14/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.ports.Event;
import org.reflections.Reflections;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@SpringComponent @Scope(SCOPE_SINGLETON)
public class ClassResolver
{
    private final Map<String, Class<? extends Event>>map = new HashMap<>();

    // POSTCONSTRUCT:
    //--------------------------------------------------------------------------------------------------------

    @PostConstruct
    public void loadDomainEventClasses()
    {   loadDomainEventClassesFrom("com.ddd"); }

    public void loadDomainEventClassesFrom(String basePackage)
    {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<? extends Event>> eventClasses = reflections.getSubTypesOf(Event.class);
        loadDomainEventClasses(eventClasses);
    }

    public void loadDomainEventClasses(Collection<Class<? extends Event>> eventClasses)
    {   eventClasses.forEach(aClass -> map.put(aClass.getSimpleName(), aClass));}

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public Class<?>getClass(String className)
    {
        if (!map.containsKey(className))
            throw new IllegalArgumentException(format("No class exist for %s", className));

        return map.get(className);
    }
}