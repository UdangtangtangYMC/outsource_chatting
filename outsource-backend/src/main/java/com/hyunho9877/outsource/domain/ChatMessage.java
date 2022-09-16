package com.hyunho9877.outsource.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ChatMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    @ManyToOne
    private ChatRoom room;
    private String sender;
    private String receiver;
    private String message;
    private String timestamp;
    private boolean confirmed;

    @PrePersist
    public void prePersist() {
        if(this.timestamp.equals("")) this.timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
        this.confirmed = false;
    }
}
