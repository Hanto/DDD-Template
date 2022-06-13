package com.ddd.context.infraestructure.bus;// Created by jhant on 12/06/2022.

import com.ddd.common.shared.annotations.SpringComponent;
import com.ddd.context.domain.querybus.Query;
import com.ddd.context.domain.querybus.QueryBus;
import com.ddd.context.domain.querybus.QueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@SpringComponent @Primary @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor @Log4j2 @SuppressWarnings({"rawtypes", "unchecked"})
public class QueryBusSpring implements QueryBus
{
    @Autowired private final ApplicationContext applicationContext;
    private final Map<Class<? extends Query>, QueryHandler> handlerMap = new HashMap<>();
    private static final String CORE_PACKAGE = "com.ddd";

    // LOAD HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    @PostConstruct
    public void createHandlers()
    {   findAndLoadQueryHandlers(); }

    // EXECUTE:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public <T> T send(Query<T> query)
    {
        if  (!handlerMap.containsKey(query.getClass()))
            throw new IllegalArgumentException(format("There are no Queries to handle: %s", query.getClass().getSimpleName()));

        QueryHandler handler = handlerMap.get(query.getClass());

        return (T) handler.handle(query);
    }

    // HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    private void findAndLoadQueryHandlers()
    {
        Reflections reflections = new Reflections(CORE_PACKAGE);
        Collection<Class<? extends QueryHandler>> handlerClasses = reflections.getSubTypesOf(QueryHandler.class);

        for (Class<? extends QueryHandler> handlerClass: handlerClasses)
        {
            ParameterizedType paramType = (ParameterizedType) handlerClass.getGenericInterfaces()[0];
            Class<? extends Query>commandClass = (Class<? extends Query>)paramType.getActualTypeArguments()[0];

            QueryHandler handler = applicationContext.getBean(handlerClass);

            handlerMap.put(commandClass, handler);
        }
    }
}