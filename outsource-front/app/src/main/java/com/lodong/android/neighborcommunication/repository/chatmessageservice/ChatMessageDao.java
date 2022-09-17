package com.lodong.android.neighborcommunication.repository.chatmessageservice;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;

import java.util.List;

@Dao
public interface ChatMessageDao {
    @Insert(onConflict = REPLACE)
    void insert(ChatMessageDTO chatMessageDTO);

    @Delete
    void delete(ChatMessageDTO chatMessageDTO);

    @Query("SELECT * FROM chatMessage WHERE chatId = :chatRoomId")
    LiveData<List<ChatMessageDTO>> getChatMessage(long chatRoomId);
}
