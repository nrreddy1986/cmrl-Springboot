package com.shellinfo.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); // ✅ important for receiving messages topic for broadcast/group chat and queue for 1-1 chat
        config.setApplicationDestinationPrefixes("/app"); // ✅ required for sending
        config.setUserDestinationPrefix("/user"); // ✅ private messaging
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat/websocket")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // ✅ IMPORTANT
    }
}