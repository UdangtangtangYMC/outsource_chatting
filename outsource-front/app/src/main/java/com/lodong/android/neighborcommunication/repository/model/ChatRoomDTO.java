package com.lodong.android.neighborcommunication.repository.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatRoom")
public class ChatRoomDTO {
    @PrimaryKey(autoGenerate = false)
    private int id;
}