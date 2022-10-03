package com.lodong.android.neighborcommunication.repository.chatmessageservice;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;

import java.util.List;

@Dao
public interface ChatMessageDao {
    @Insert(onConflict = IGNORE)
    Long insert(ChatMessageDTO chatMessageDTO);

    @Delete
    void delete(ChatMessageDTO chatMessageDTO);

    @Query("SELECT * FROM chatMessage WHERE chatId = :chatId")
    ChatMessageDTO getChatMessageById(long chatId);

    @Query("SELECT * FROM chatMessage WHERE chatRoomId = :chatRoomId")
    LiveData<List<ChatMessageDTO>> getChatMessage(long chatRoomId);
}
