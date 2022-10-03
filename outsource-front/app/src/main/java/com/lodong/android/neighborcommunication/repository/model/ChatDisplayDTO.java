package com.lodong.android.neighborcommunication.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat_display")
public class ChatDisplayDTO {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "room_id")
    private Long chatRoomId;


    @ColumnInfo(name = "message_id")
    private Long messageId;

    @Ignore
    public ChatDisplayDTO(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public ChatDisplayDTO(Long chatRoomId, Long messageId) {
        this.chatRoomId = chatRoomId;
        this.messageId = messageId;
    }

    public long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
