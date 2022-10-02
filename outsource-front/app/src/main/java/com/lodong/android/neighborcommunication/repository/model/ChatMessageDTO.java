package com.lodong.android.neighborcommunication.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatMessage")
public class ChatMessageDTO {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "chatId")
    private Long chatId = 0L;

    @ColumnInfo(name = "chatRoomId")
    private Long roomId;

    @ColumnInfo(name = "sender")
    private String sender;

    @ColumnInfo(name = "receiver")
    private String receiver;

    @ColumnInfo(name = "sendTime")
    private String timestamp;

    @ColumnInfo(name = "checkReceipt")
    private boolean checkReceipt;

    @ColumnInfo(name = "content")
    private String message;

    @ColumnInfo(name = "viewType")
    private int viewType;

    @Ignore
    private String senderNickName;
    @Ignore
    private String receiverNickName;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(long roomId, String sender, String receiver, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public ChatMessageDTO(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public ChatMessageDTO(long chatId, long roomId, String sender, String receiver, String timestamp, boolean checkReceipt, String message, int viewType, String senderNickName, String receiverNickName) {
        this.chatId = chatId;
        this.roomId = roomId;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
        this.checkReceipt = checkReceipt;
        this.message = message;
        this.viewType = viewType;
        this.senderNickName = senderNickName;
        this.receiverNickName = receiverNickName;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
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

    public String getSenderNickName() {
        return senderNickName;
    }

    public void setSenderNickName(String senderNickName) {
        this.senderNickName = senderNickName;
    }

    public String getReceiverNickName() {
        return receiverNickName;
    }

    public void setReceiverNickName(String receiverNickName) {
        this.receiverNickName = receiverNickName;
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "chatId=" + chatId +
                ", roomId=" + roomId +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", checkReceipt=" + checkReceipt +
                ", message='" + message + '\'' +
                ", viewType=" + viewType +
                ", senderNickName='" + senderNickName + '\'' +
                ", receiverNickName='" + receiverNickName + '\'' +
                '}';
    }
}
