package com.hyunho9877.outsource.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hyunho9877.outsource.domain.ChatMessage;
import com.hyunho9877.outsource.domain.ChatRoom;
import com.hyunho9877.outsource.dto.ChatRoomDTO;
import com.hyunho9877.outsource.service.ChatService;
import com.hyunho9877.outsource.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
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
    private final UserService userService;

    @MessageMapping("/msg")
    public void sendMessage(@Payload ChatMessage message) throws FirebaseMessagingException {
        if(userService.isBlocked(message.getSender(), message.getReceiver())) return;
        chatService.send(message);
        chatService.sendNotification(message);
    }

    @PostMapping("/new")
    public ResponseEntity<ChatRoom> newChat(@RequestBody ChatRoomDTO chatRoomDTO) {
        return ResponseEntity.ok(chatService.registerNewChatRoom(chatRoomDTO.getId(), chatRoomDTO.getSubject()));
    }
}
