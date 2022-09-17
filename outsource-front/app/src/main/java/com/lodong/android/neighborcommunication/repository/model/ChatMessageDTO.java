package com.lodong.android.neighborcommunication.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatMessage")
public class ChatMessageDTO {
    @PrimaryKey(autoGenerate = false)
    private long chatId;

    @ColumnInfo(name = "chatRoomId")
    private long room;

    @ColumnInfo(name="sender")
    private String sender;

    @ColumnInfo(name="receiver")
    private String receiver;

    @ColumnInfo(name="sendTime")
    private String timestamp;

    @ColumnInfo(name="checkReceipt")
    private boolean checkReceipt;

    @ColumnInfo(name="content")
    private String message;

    @ColumnInfo(name = "viewType")
    private int viewType;

    public ChatMessageDTO(long chatId, long chatRoomId, String sender, String receiver, String sendTime, boolean checkReceipt, String content, int viewType) {
        this.chatId = chatId;
        this.room = chatRoomId;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = sendTime;
        this.checkReceipt = checkReceipt;
        this.message = content;
        this.viewType = viewType;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getRoom() {
        return room;
    }

    public void setRoom(long room) {
        this.room = room;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isCheckReceipt() {
        return checkReceipt;
    }

    public void setCheckReceipt(boolean checkReceipt) {
        this.checkReceipt = checkReceipt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "chatId=" + chatId +
                ", room=" + room +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", checkReceipt=" + checkReceipt +
                ", message='" + message + '\'' +
                ", viewType=" + viewType +
                '}';
    }
}
