package com.hyunho9877.outsource.controller;

import com.hyunho9877.outsource.domain.ChatMessage;
import com.hyunho9877.outsource.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/hello")
    public void newUser(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        log.info("{}", message);
        chatService.send(message);
    }

    @PostMapping("/new")
    public ResponseEntity<?> newChat(@RequestBody String id, @RequestBody String subject) {
        chatService.registerNewChatRoom(id, subject);
        return ResponseEntity.ok(null);
    }
}
