package com.ddd.context.infraestructure.bus;// Created by jhant on 12/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.domain.querybus.Query;
import com.ddd.context.domain.querybus.QueryBus;
import com.ddd.context.domain.querybus.QueryHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@SpringComponent @Scope(SCOPE_SINGLETON) @Primary
@Log4j2 @SuppressWarnings({"rawtypes", "unchecked"})
public class QueryBusSpring implements QueryBus
{
    private final Map<Class<?>, QueryHandler> handlerMap = new HashMap<>();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    @Autowired
    public QueryBusSpring(List<QueryHandler<?, ? extends Query<?>>> handlers)
    {
        for (QueryHandler<?, ? extends Query<?>> handler : handlers)
            handlerMap.put(getQuery(handler), handler);
    }

    // EXECUTE:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public <T> T send(Query<T> query)
    {
        if  (!handlerMap.containsKey(query.getClass()))
            throw new IllegalArgumentException(format("There are no Queries to handle: %s", query.getClass().getSimpleName()));

        return (T) handlerMap.get(query.getClass()).handle(query);
    }

    // LOAD HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    private Class<?> getQuery(QueryHandler<?, ? extends Query<?>>handler)
    {
        Class<?>handlerClass = getClass(handler);

        Type commandType = Arrays.stream(handlerClass.getGenericInterfaces())
            .filter(ParameterizedType.class::isInstance)
            .map(ParameterizedType.class::cast)
            .filter(this::isAQueryHandler)
            .map(type -> type.getActualTypeArguments()[0])
            .findFirst().orElseThrow(() -> new RuntimeException(format("Invalid QueryHandler %s", handlerClass)));

        return (Class<?>) commandType;
    }

    private Class<?>getClass(Object clazz)
    {
        return clazz instanceof Advised ?
            ((Advised) clazz).getTargetClass() :
            clazz.getClass();
    }

    private boolean isAQueryHandler(ParameterizedType type)
    {   return type.getRawType().getTypeName().equals(QueryHandler.class.getName()); }
}