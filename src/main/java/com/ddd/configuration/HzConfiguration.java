package com.ddd.configuration;// Created by jhant on 03/06/2022.

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration @EnableCaching
@RequiredArgsConstructor
public class HzConfiguration
{
    @Autowired private final ResourcePatternResolver resourceResolver;

    // MEMORY DATA GRID:
    //--------------------------------------------------------------------------------------------------------

    /*@Bean
    public HazelcastInstance hazelcastInstance() throws IOException
    {
        URL configURL = resourceResolver.getResource("classpath:hazelcast.yml").getURL();
        Config config = new YamlConfigBuilder(configURL).build();

        return Hazelcast.newHazelcastInstance(config);
    }*/
}