package com.lodong.android.neighborcommunication.repository.chatroomservice;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;

import java.util.List;

@Dao
public interface ChatRoomDao {
    @Insert(onConflict = REPLACE)
    void insert(ChatRoomDTO chatRoomDTO);

    @Delete
    void delete(ChatRoomDTO chatRoomDTO);

    @Query("SELECT * FROM chatRoom")
    LiveData<List<ChatRoomDTO>> getAll();

}
