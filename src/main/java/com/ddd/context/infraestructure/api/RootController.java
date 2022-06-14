package com.ddd.context.infraestructure.api;// Created by jhant on 07/06/2022.

import com.ddd.context.domain.events.DomainEvent;
import com.ddd.context.domain.events.EventBus;
import com.ddd.context.domain.events.ExampleEvent;
import com.ddd.context.infraestructure.api.dtos.ApiDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController @RequestMapping(value ="/api", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@RequiredArgsConstructor @SuppressWarnings("all")
@Log4j2
public class RootController
{
    // ROOT: (discovery)
    //--------------------------------------------------------------------------------------------------------

    @GetMapping("")
    public ApiDTO getIndex() throws NoSuchMethodException
    {
        ApiDTO api = new ApiDTO();

        api.add(linkTo(methodOn(RootController.class).getIndex()).withSelfRel());

        return api;
    }

    @Autowired private EventBus eventBus;
    @GetMapping("test")
    public void test()
    {
        DomainEvent event = new ExampleEvent("1L", "Prueba", 21, true);
        eventBus.publish(Arrays.asList(event));
    }
}