package com.ddd.common.nonshared;// Created by jhant on 10/06/2022.

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class ObjectSerializer
{
    @Autowired private final ObjectMapper objectMapper;

    private static final String TYPE_NODE_NAME = "type";

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
    public <T> T fromJson(String json, String basePackage, String simpleClassName)
    {
        try
        {
            String className = format("%s.%s", basePackage, simpleClassName);
            Class<?> clazz = Class.forName(className);
            return (T) objectMapper.readValue(json, clazz);
        }
        catch (Exception e)
        {   throw new IllegalArgumentException("Cannot deserialize it", e); }
    }

    @SuppressWarnings("unchecked")
    public <T> T fromJson(String json, String basePackage)
    {
        try
        {
            ObjectNode node = objectMapper.readValue(json, ObjectNode.class);
            String className = format("%s.%s", basePackage, node.get(TYPE_NODE_NAME).asText());
            Class<?> clazz = Class.forName(className);
            return (T) objectMapper.readValue(json, clazz);
        }
        catch (Exception e)
        {   throw new IllegalArgumentException("Cannot deserialize it", e); }
    }
}