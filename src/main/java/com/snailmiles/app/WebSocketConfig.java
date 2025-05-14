package com.snailmiles.app;

import com.snailmiles.app.DTO.transfer.TransferSessionObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final Map<String, TransferSessionObject> tranferSessions;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/user");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/v1/socket").setAllowedOrigins("*");
        registry.addEndpoint("/api/v1/socket/global").setAllowedOrigins("*");

    }



    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String userId = accessor.getFirstNativeHeader("topic");
                    log.info("New connection: sessionId = " + accessor.getSessionId() + " uuid: " + userId);
                } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
                    log.info("Disconnected: sessionId = " + accessor.getSessionId());
                }else if(StompCommand.SUBSCRIBE.equals(accessor.getCommand())){
                    String sessionId = accessor.getSessionId();
                    String destination = accessor.getDestination();
                    if(accessor.getFirstNativeHeader("isTransfer") != null){
                        String uuid = accessor.getFirstNativeHeader("uuid");
                        String secret_ket = accessor.getFirstNativeHeader("secret_key");
                        String points_to_be_transfered = accessor.getFirstNativeHeader("points_to_be_transfered");
                        log.info(secret_ket + " was put in sessions");
                        tranferSessions.put(secret_ket, TransferSessionObject.
                                builder().withUuid(uuid).
                                withSecret_key(secret_ket).
                                withPoints_to_be_transfered(points_to_be_transfered)
                                .build());
                    }

                    log.info("New subscription:");
                    log.info("Session ID: " + sessionId);
                    log.info("Destination: " + destination);
                }else if(StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())){
                    String sessionId = accessor.getSessionId();
                    if(accessor.getFirstNativeHeader("isTransfer") != null){
                        String secret_ket = accessor.getFirstNativeHeader("secret_ket");
                        log.info(secret_ket + " removed from sessions");

                        tranferSessions.values().remove(secret_ket);
                    }
                }
                return message;
            }
        });
    }

}
