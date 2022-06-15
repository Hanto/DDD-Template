package com.ddd.context.infraestructure.bus;// Created by jhant on 13/06/2022.

import com.ddd.common.annotations.SpringComponent;
import com.ddd.context.application.ports.Command;
import com.ddd.context.application.ports.CommandBus;
import com.ddd.context.application.ports.CommandHandler;
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
public class CommandBusSpring implements CommandBus
{
    private final Map<Class<?>, CommandHandler> handlerMap = new HashMap<>();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    @Autowired
    public CommandBusSpring(List<CommandHandler<? extends Command>> handlers)
    {
        for (CommandHandler<? extends Command> handler : handlers)
            handlerMap.put(getCommand(handler), handler);
    }

    // SEND:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void send(Command command)
    {
        if (!handlerMap.containsKey(command.getClass()))
            throw new IllegalArgumentException(format("There are no commands to handle: %s", command.getClass().getSimpleName()));

        handlerMap.get(command.getClass()).handle(command);
    }

    // LOAD HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    private Class<?> getCommand(CommandHandler<?>handler)
    {
        Class<?>handlerClass = getClass(handler);

        Type commandType = Arrays.stream(handlerClass.getGenericInterfaces())
            .filter(ParameterizedType.class::isInstance)
            .map(ParameterizedType.class::cast)
            .filter(this::isACommandHandler)
            .map(type -> type.getActualTypeArguments()[0])
            .findFirst().orElseThrow(() -> new RuntimeException(format("Invalid CommandHandler %s", handlerClass)));

        return (Class<?>) commandType;
    }

    private Class<?>getClass(Object clazz)
    {
        return clazz instanceof Advised ?
            ((Advised) clazz).getTargetClass() :
            clazz.getClass();
    }

    private boolean isACommandHandler(ParameterizedType type)
    {   return type.getRawType().getTypeName().equals(CommandHandler.class.getName()); }
}