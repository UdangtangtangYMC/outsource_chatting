package com.lodong.android.neighborcommunication.data;

public class ChatRoomDisplayInfo {
    private String receiverNickName;
    private String receiver;
    private long roomId;
    private String roomUserOne;
    private String roomUserTwo;
    private String roomUserOneNickName;
    private String roomUserTwoNickName;

    private String lastMessage;
    private String lastMessageTime;

    public ChatRoomDisplayInfo(String receiverNickName, long roomId, String roomUserOne, String roomUserTwo, String roomUserOneNickName, String roomUserTwoNickName, String lastMessage, String lastMessageTime,String receiver) {
        this.receiverNickName = receiverNickName;
        this.receiver = receiver;
        this.roomId = roomId;
        this.roomUserOne = roomUserOne;
        this.roomUserTwo = roomUserTwo;
        this.roomUserOneNickName = roomUserOneNickName;
        this.roomUserTwoNickName = roomUserTwoNickName;
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

    public String getRoomUserOne() {
        return roomUserOne;
    }

    public void setRoomUserOne(String roomUserOne) {
        this.roomUserOne = roomUserOne;
    }

    public String getRoomUserTwo() {
        return roomUserTwo;
    }

    public void setRoomUserTwo(String roomUserTwo) {
        this.roomUserTwo = roomUserTwo;
    }

    public String getRoomUserOneNickName() {
        return roomUserOneNickName;
    }

    public void setRoomUserOneNickName(String roomUserOneNickName) {
        this.roomUserOneNickName = roomUserOneNickName;
    }

    public String getRoomUserTwoNickName() {
        return roomUserTwoNickName;
    }

    public void setRoomUserTwoNickName(String roomUserTwoNickName) {
        this.roomUserTwoNickName = roomUserTwoNickName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
