package com.ddd.context.adapter.bus;// Created by jhant on 12/06/2022.

import com.ddd.common.shared.annotations.SpringComponent;
import com.ddd.context.domain.commands.Command;
import com.ddd.context.domain.commands.CommandBus;
import com.ddd.context.domain.commands.CommandHandler;
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
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@SpringComponent @Primary @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor @Log4j2 @SuppressWarnings({"rawtypes", "unchecked"})
public class CommandBusSpring implements CommandBus
{
    @Autowired private final ApplicationContext applicationContext;
    private final Map<Class<? extends Command>, Class<? extends CommandHandler>> handlerMap = new HashMap<>();

    // LOAD HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    @PostConstruct
    public void createHandlers()
    {   findAndLoadCommandHandlers(); }

    // EXECUTE:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void send(Command command)
    {
        if (!handlerMap.containsKey(command.getClass()))
            throw new IllegalArgumentException(format("There are no commands to handle: %s", command.getClass().getSimpleName()));

        Class<? extends CommandHandler> handlerClass = handlerMap.get(command.getClass());
        CommandHandler handler = applicationContext.getBean(handlerClass);
        handler.handle(command);
    }

    // HANDLE:
    //--------------------------------------------------------------------------------------------------------

    private void findAndLoadCommandHandlers()
    {
        Reflections reflections = new Reflections("com.ddd");
        Collection<Class<? extends CommandHandler>> handlers = reflections.getSubTypesOf(CommandHandler.class);

        for (Class<? extends CommandHandler> handler: handlers)
        {
            ParameterizedType paramType = (ParameterizedType) handler.getGenericInterfaces()[0];
            Class<? extends Command>commandClass = (Class<? extends Command>)paramType.getActualTypeArguments()[0];

            handlerMap.put(commandClass, handler);

        }
    }

    private void findAndLoadCommandHandlers2()
    {
        List<CommandHandler> handlers = applicationContext.getBeansOfType(CommandHandler.class).values().stream().toList();

        for (CommandHandler<?> handler: handlers)
        {
            ParameterizedType paramType = (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
            Class<? extends Command>commandClass = (Class<? extends Command>)paramType.getActualTypeArguments()[0];

            handlerMap.put(commandClass, handler.getClass());
        }
    }
}