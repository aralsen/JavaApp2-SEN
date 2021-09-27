package org.csystem.application.server.sendreceivefile.configuration.client;

import org.csystem.application.server.sendreceivefile.client.ClientInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ClientInfoConfig {
    @Bean
    public Map<Socket, ClientInfo> getClientMap()
    {
        return new ConcurrentHashMap<>();
    }
}
