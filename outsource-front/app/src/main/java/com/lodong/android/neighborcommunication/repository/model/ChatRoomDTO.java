package com.lodong.android.neighborcommunication.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatRoom")
public class ChatRoomDTO {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "room_id")
    private long roomId;

    @ColumnInfo(name = "room_user_one_id")
    private String roomUserOneId;

    @ColumnInfo(name = "room_user_two_id")
    private String roomUserTwoId;

    @ColumnInfo(name = "room_user_one_nick_name")
    private String roomUserOneNickName;

    @ColumnInfo(name = "room_user_two_nick_name")
    private String roomUserTwoNickName;

    public ChatRoomDTO(long roomId, String roomUserOneId, String roomUserTwoId, String roomUserOneNickName, String roomUserTwoNickName) {
        this.roomId = roomId;
        this.roomUserOneId = roomUserOneId;
        this.roomUserTwoId = roomUserTwoId;
        this.roomUserOneNickName = roomUserOneNickName;
        this.roomUserTwoNickName = roomUserTwoNickName;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomUserOneId() {
        return roomUserOneId;
    }

    public void setRoomUserOneId(String roomUserOneId) {
        this.roomUserOneId = roomUserOneId;
    }

    public String getRoomUserTwoId() {
        return roomUserTwoId;
    }

    public void setRoomUserTwoId(String roomUserTwoId) {
        this.roomUserTwoId = roomUserTwoId;
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
                ", roomUserOne='" + roomUserOneId + '\'' +
                ", roomUserTwo='" + roomUserTwoId + '\'' +
                ", roomUserOneNickName='" + roomUserOneNickName + '\'' +
                ", roomUserTwoNickName='" + roomUserTwoNickName + '\'' +
                '}';
    }
}