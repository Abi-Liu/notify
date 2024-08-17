package com.abiliu.notify.config;

import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ExecutorService taskExecutor() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        return executor;
    }
}
