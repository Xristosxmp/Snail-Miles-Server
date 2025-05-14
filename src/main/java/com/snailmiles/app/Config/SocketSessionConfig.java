package com.snailmiles.app.Config;

import com.snailmiles.app.DTO.transfer.TransferSessionObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SocketSessionConfig {

    @Bean("tranferSessions")
    public Map<String, TransferSessionObject> tranferSessions() {
        return new ConcurrentHashMap<>();
    }
}

