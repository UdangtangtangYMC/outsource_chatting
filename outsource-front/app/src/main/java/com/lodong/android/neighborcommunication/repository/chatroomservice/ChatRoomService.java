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

    public LiveData<List<ChatRoomDTO>> getChatRoomList(){
        chatRoomList = chatRoomDao.getAll();
        return chatRoomList;
    }

    public void insert(ChatRoomDTO chatRoomDTO){
        chatRoomDao.insert(chatRoomDTO);
    }

    public void delete(ChatRoomDTO chatRoomDTO) {
        chatRoomDao.delete(chatRoomDTO);
    }

    public boolean isExists(String p1, String p2) {
        return chatRoomDao.existsByParticipants(p1, p2);
    }
}
