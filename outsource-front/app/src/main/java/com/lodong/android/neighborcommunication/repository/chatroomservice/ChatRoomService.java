package com.lodong.android.neighborcommunication.repository.chatroomservice;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.roomdb.RoomDB;

import java.util.List;

public class ChatRoomService {
    private ChatRoomDao chatRoomDao;
    private LiveData<List<ChatRoomDTO>> chatRoomList;

    public ChatRoomService(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        chatRoomDao = db.chatRoomDao();
        chatRoomList = chatRoomDao.getAll();
    }

    public void insert(ChatRoomDTO chatRoomDTO){
        chatRoomDao.insert(chatRoomDTO);
    }

    public void delete(ChatRoomDTO chatRoomDTO){
        chatRoomDao.delete(chatRoomDTO);
    }
}
