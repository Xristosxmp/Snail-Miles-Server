package com.snailmiles.app.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SocketSessionConfig {

    @Bean("socketSessions")
    public Map<String, String> socketSessions() {
        return new ConcurrentHashMap<>();
    }
}

