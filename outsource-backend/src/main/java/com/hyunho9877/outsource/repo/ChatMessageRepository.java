package com.hyunho9877.outsource.repo;

import com.hyunho9877.outsource.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}