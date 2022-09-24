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
    private long chatRoomId;

    @ColumnInfo(name = "message_id")
    private long messageId;

    @Ignore
    public ChatDisplayDTO(long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public ChatDisplayDTO(long chatRoomId, long messageId) {
        this.chatRoomId = chatRoomId;
        this.messageId = messageId;
    }

    public long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
