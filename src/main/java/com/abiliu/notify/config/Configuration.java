package com.abiliu.notify.config;

import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Executor taskExecutor() {
        Executor executor = Executors.newFixedThreadPool(10);
        return executor;
    }
}
