package com.lodong.android.neighborcommunication.repository.ChatDisplayService;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lodong.android.neighborcommunication.repository.model.ChatDisplayDTO;

import java.util.List;

@Dao
public interface ChatDisplayDao {
    @Insert(onConflict = REPLACE)
    void insert(ChatDisplayDTO chatDisplayDTO);

    @Delete
    void delete(ChatDisplayDTO chatDisplayDTO);

    @Query("UPDATE `chat_display` SET `message_id` =  :messageId WHERE `room_id`= :roomId")
    void updateValue(long roomId, long messageId);


    @Query("SELECT * FROM chat_display")
    LiveData<List<ChatDisplayDTO>> getAll();
}