package com.hyunho9877.outsource.service;

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

    }

    @Transactional
    public void confirmMessage(String username, String sender) {

    }

    public void registerNewChatRoom(String requester, String subject) {
        if(isChatRoomAlreadyExists(requester, subject)) throw new IllegalStateException();
        ApplicationUser req = userRepository.getReferenceById(requester);
        ApplicationUser sub = userRepository.getReferenceById(subject);

        ChatRoom chatRoom = ChatRoom.builder()
                .roomUserOne(req)
                .roomUserTwo(sub)
                .build();
        chatRoomRepository.save(chatRoom);
    }

    private boolean isChatRoomAlreadyExists(String requester, String subject) {
        return chatRoomRepository.existsByUserOneAndUserTwo(requester, subject);
    }
}
