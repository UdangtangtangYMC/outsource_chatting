package com.hyunho9877.outsource.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.gson.Gson;
import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.domain.ChatMessage;
import com.hyunho9877.outsource.domain.ChatRoom;
import com.hyunho9877.outsource.repo.ApplicationUserRepository;
import com.hyunho9877.outsource.repo.ChatMessageRepository;
import com.hyunho9877.outsource.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final RabbitTemplate rabbitTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository messageRepository;
    private final ApplicationUserRepository userRepository;

    @Transactional
    public void send(ChatMessage message) {
        ApplicationUser sender = userRepository.findById(message.getSender()).orElseThrow();
        ApplicationUser receiver = userRepository.findById(message.getReceiver()).orElseThrow();
        ChatRoom chatRoom = chatRoomRepository.findById(message.getRoomId()).orElseThrow();
        message.setRoom(chatRoom);

        ChatMessage saved = messageRepository.save(message);
        log.info("message saved : {}", saved);

        saved.setSenderNickName(sender.getNickName());
        saved.setReceiverNickName(receiver.getNickName());

        rabbitTemplate.convertAndSend(message.getReceiver(), message);
    }

    public ChatRoom registerNewChatRoom(String requester, String subject) {
        if(isChatRoomAlreadyExists(requester, subject)){
            ChatRoom publicRoomDetails = ChatRoom.getPublicRoomDetails(chatRoomRepository.findByRoomUserOneAndRoomUserTwo(requester, subject).orElseThrow());
            log.debug("already exists : {}", publicRoomDetails);
            return publicRoomDetails;
        }
        ApplicationUser req = userRepository.findById(requester).orElseThrow();
        ApplicationUser sub = userRepository.findById(subject).orElseThrow();

        ChatRoom chatRoom = ChatRoom.builder()
                .roomUserOne(req)
                .roomUserTwo(sub)
                .build();

        ChatRoom save = chatRoomRepository.save(chatRoom);
        ChatRoom publicRoomDetails = ChatRoom.getPublicRoomDetails(save);
        log.info("room Created : {}", publicRoomDetails);
        return publicRoomDetails;
    }

    private boolean isChatRoomAlreadyExists(String requester, String subject) {
        return chatRoomRepository.existsByUserOneAndUserTwo(requester, subject);
    }

    public void sendNotification(ChatMessage chat) throws FirebaseMessagingException, IllegalArgumentException {
        ApplicationUser applicationUser = userRepository.findById(chat.getReceiver()).orElseThrow();
        if(!applicationUser.isReceiveNotification()) return;
        String token = applicationUser.getFcmToken();
        Message message = Message.builder()
                .putData("title", chat.getSender())
                .putData("body", chat.getMessage())
                .putData("data", new Gson().toJson(ChatMessage.getPublicMessageDetails(chat)))
                .setToken(token)
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
        log.info("chatId : {}, FCM response : {}", chat.getChatId(), response);
    }

    public ChatRoom getChatRoomId(String id, String subject) {
        return ChatRoom.getPublicRoomDetails(chatRoomRepository.findByRoomUserOneAndRoomUserTwo(id, subject).orElseThrow());
    }
}
