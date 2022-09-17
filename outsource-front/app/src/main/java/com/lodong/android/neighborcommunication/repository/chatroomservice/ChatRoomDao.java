package com.lodong.android.neighborcommunication.repository.chatroomservice;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;

import java.util.List;

@Dao
public interface ChatRoomDao {
    @Insert(onConflict = IGNORE)
    void insert(ChatRoomDTO chatRoomDTO);

    @Delete
    void delete(ChatRoomDTO chatRoomDTO);

    @Query("SELECT * FROM chatRoom")
    LiveData<List<ChatRoomDTO>> getAll();

    @Query("select exists (select 1 from chatroom c where (c.room_user_one = :p1 and c.room_user_two = :p2) or (c.room_user_one = :p2 and c.room_user_two = :p1))")
    boolean existsByParticipants(String p1, String p2);
}
