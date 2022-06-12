package com.ddd.configuration;// Created by jhant on 10/06/2022.

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration @EnableAsync @EnableScheduling
@RequiredArgsConstructor
public class ThreadPools
{
    public static final String MISC_POOL01 = "MiscPool01";

    @Bean(name = MISC_POOL01)
    public Executor getExecutor01()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(2);
        executor.setThreadNamePrefix("MiscPool");
        executor.initialize();
        return executor;
    }
}
