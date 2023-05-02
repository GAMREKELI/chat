package ru.demo.chat.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("ws/").withSockJS();
//        STOMP — это Simple Text Oriented Messaging Protocol. Это протокол обмена сообщениями, задающий формат и правила обмена.
//        Зачем нужен Stomp? Дело в том, что сам по себе WebSocket не дает таких вещей (более высокого уровня),
//        как отправка сообщений пользователям, подписанным на тему, или отправка сообщений конкретному пользователю.
    }

    // Настраиваем брокер сообщений, он будет использоваться для отправки сообщений от одного клиента к другому
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
