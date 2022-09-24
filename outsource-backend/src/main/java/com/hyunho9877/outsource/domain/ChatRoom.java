package com.hyunho9877.outsource.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @ManyToOne
    private ApplicationUser roomUserOne;
    @ManyToOne
    private ApplicationUser roomUserTwo;
    @Transient
    private String roomUserOneNickName;
    @Transient
    private String roomUserTwoNickName;
    @Transient
    private String roomUserOneId;
    @Transient
    private String roomUserTwoId;

    public static ChatRoom getPublicRoomDetails(ChatRoom chatRoom) {
        return new ChatRoom(chatRoom.getRoomId(), null, null, chatRoom.getRoomUserOne().getNickName(), chatRoom.getRoomUserTwo().getNickName(), chatRoom.getRoomUserOne().getId(), chatRoom.getRoomUserTwo().getId());
    }
}
