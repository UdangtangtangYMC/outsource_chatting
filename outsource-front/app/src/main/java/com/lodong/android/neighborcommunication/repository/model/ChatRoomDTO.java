package com.lodong.android.neighborcommunication.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatRoom")
public class ChatRoomDTO {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "room_id")
    private long roomId;

    @ColumnInfo(name = "room_user_one")
    private String roomUserOne;

    @ColumnInfo(name = "room_user_two")
    private String roomUserTwo;

    @ColumnInfo(name = "room_user_one_nick_name")
    private String roomUserOneNickName;

    @ColumnInfo(name = "room_user_two_nick_name")
    private String roomUserTwoNickName;

    public ChatRoomDTO(long roomId, String roomUserOne, String roomUserTwo) {
        this.roomId = roomId;
        this.roomUserOne = roomUserOne;
        this.roomUserTwo = roomUserTwo;
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

    @Override
    public String toString() {
        return "ChatRoomDTO{" +
                "roomId=" + roomId +
                ", roomUserOne='" + roomUserOne + '\'' +
                ", roomUserTwo='" + roomUserTwo + '\'' +
                ", roomUserOneNickName='" + roomUserOneNickName + '\'' +
                ", roomUserTwoNickName='" + roomUserTwoNickName + '\'' +
                '}';
    }
}