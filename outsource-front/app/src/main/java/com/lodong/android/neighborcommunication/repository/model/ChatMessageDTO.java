package com.lodong.android.neighborcommunication.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatMessage")
public class ChatMessageDTO {
    @PrimaryKey(autoGenerate = false)
    private long id;

    @ColumnInfo(name = "chatRoomId")
    private long chatRoomId;

    @ColumnInfo(name="sender")
    private String sender;

    @ColumnInfo(name="receiver")
    private String receiver;

    @ColumnInfo(name="sendTime")
    private String sendTime;

    @ColumnInfo(name="checkReceipt")
    private boolean checkReceipt;

    @ColumnInfo(name="content")
    private String content;

    @ColumnInfo(name = "viewType")
    private int viewType;

    public ChatMessageDTO(long id, long chatRoomId, String sender, String receiver, String sendTime, boolean checkReceipt, String content, int viewType) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.receiver = receiver;
        this.sendTime = sendTime;
        this.checkReceipt = checkReceipt;
        this.content = content;
        this.viewType = viewType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(long chatRoomId) {
        this.chatRoomId = chatRoomId;
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isCheckReceipt() {
        return checkReceipt;
    }

    public void setCheckReceipt(boolean checkReceipt) {
        this.checkReceipt = checkReceipt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
