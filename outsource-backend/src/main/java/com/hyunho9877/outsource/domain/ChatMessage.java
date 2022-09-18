package com.hyunho9877.outsource.domain;

import com.google.common.base.Strings;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Getter @Setter @ToString @Builder
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
    @Transient
    private String senderNickName;
    @Transient
    private String receiverNickName;
    @Transient
    private long roomId;

    @PrePersist
    public void prePersist() {
        if(Strings.isNullOrEmpty(timestamp)) this.timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
        this.confirmed = false;
    }
}
