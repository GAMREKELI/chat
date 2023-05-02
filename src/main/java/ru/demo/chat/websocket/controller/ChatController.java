package ru.demo.chat.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ru.demo.chat.websocket.model.ChatMessage;

@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage") // будет вызван, когда клиент отправляет сообщение на /app/chat.sendMessage.
    @SendTo("/topic/public") // результат выполнения этого метода будет отправлен на топик /topic/public.
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser") // будет вызван, когда клиент отправляет сообщение на /app/chat.addUser.
    @SendTo("/topic/public") // результат выполнения этого метода будет отправлен на топик /topic/public.
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("usermane", chatMessage.getSender());
        return chatMessage;

    }
}
