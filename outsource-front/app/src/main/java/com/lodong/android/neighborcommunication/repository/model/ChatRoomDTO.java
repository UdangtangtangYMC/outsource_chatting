package com.lodong.android.neighborcommunication.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatRoom")
public class ChatRoomDTO {
    @PrimaryKey(autoGenerate = false)
    private long id;

    @ColumnInfo(name = "participant1")
    private String participant1;

    @ColumnInfo(name = "participant2")
    private String participant2;

    public ChatRoomDTO(long id, String participant1, String participant2) {
        this.id = id;
        this.participant1 = participant1;
        this.participant2 = participant2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParticipant1() {
        return participant1;
    }

    public void setParticipant1(String participant1) {
        this.participant1 = participant1;
    }

    public String getParticipant2() {
        return participant2;
    }

    public void setParticipant2(String participant2) {
        this.participant2 = participant2;
    }
}