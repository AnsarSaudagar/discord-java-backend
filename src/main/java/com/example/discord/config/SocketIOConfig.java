package com.example.discord.config;

import org.springframework.context.annotation.Bean;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Configuration;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {
    @Bean
    public SocketIOServer socketIOServer(){
        Configuration config = new Configuration(); 
        config.setHostname("localhost");
        config.setPort(9092); // Port for Socket.IO server

        // Allow cross-origin requests
        config.setOrigin("*");
        return new SocketIOServer(config);
    }
}
