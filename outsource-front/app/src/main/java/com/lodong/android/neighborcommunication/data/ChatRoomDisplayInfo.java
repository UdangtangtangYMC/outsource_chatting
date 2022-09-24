package com.lodong.android.neighborcommunication.data;

public class ChatRoomDisplayInfo {
    private long roomId;
    private String receiver;
    private String receiverNickName;
    private String lastMessage;
    private String lastMessageTime;

    public ChatRoomDisplayInfo(String receiverNickName, String receiver, long roomId, String lastMessage, String lastMessageTime) {
        this.receiverNickName = receiverNickName;
        this.receiver = receiver;
        this.roomId = roomId;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }

    public String getReceiverNickName() {
        return receiverNickName;
    }

    public void setReceiverNickName(String receiverNickName) {
        this.receiverNickName = receiverNickName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
