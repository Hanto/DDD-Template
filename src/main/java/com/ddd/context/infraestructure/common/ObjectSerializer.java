package com.ddd.context.infraestructure.common;// Created by jhant on 10/06/2022.

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectSerializer
{
    @Autowired private final ObjectMapper objectMapper;
    @Autowired private final ClassResolver classResolver;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public String toJson(Object object)
    {
        try
        {   return objectMapper.writeValueAsString(object); }
        catch (Exception e)
        {   throw new IllegalArgumentException("Cannot serialize it", e); }
    }

    public <T> T fromJson(String json, Class<T>clazz)
    {
        try
        {   return objectMapper.readValue(json, clazz); }
        catch (Exception e)
        {   throw new IllegalArgumentException("Cannot deserialize it", e); }
    }

    @SuppressWarnings("unchecked")
    public <T> T fromEventJson(String json, String simpleClassName)
    {
        try
        {
            Class<?> clazz = classResolver.getClass(simpleClassName);
            return (T) objectMapper.readValue(json, clazz);
        }
        catch (Exception e)
        {   throw new IllegalArgumentException("Cannot deserialize it", e); }
    }
}