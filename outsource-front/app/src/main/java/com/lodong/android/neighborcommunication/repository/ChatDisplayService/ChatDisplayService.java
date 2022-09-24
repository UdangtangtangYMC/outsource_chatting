package com.lodong.android.neighborcommunication.repository.ChatDisplayService;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lodong.android.neighborcommunication.repository.chatmessageservice.ChatMessageDao;
import com.lodong.android.neighborcommunication.repository.model.ChatDisplayDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.roomdb.RoomDB;

import java.util.List;

public class ChatDisplayService {
    private ChatDisplayDao chatDisplayDao;
    private LiveData<List<ChatDisplayDTO>> chatDisplayList;

    public ChatDisplayService(Application application){
        RoomDB db = RoomDB.getInstance(application);
        chatDisplayDao = db.chatDisplayDao();
    }

    public LiveData<List<ChatDisplayDTO>> getChatMessageList() {
        return chatDisplayList;
    }

    public LiveData<List<ChatDisplayDTO>> getAll(){
        chatDisplayList = chatDisplayDao.getAll();
        return chatDisplayList;
    }

    public void insert(ChatDisplayDTO chatDisplayDTO){
        chatDisplayDao.insert(chatDisplayDTO);
    }

    public void delete(ChatDisplayDTO chatMessageDTO){
        chatDisplayDao.delete(chatMessageDTO);
    }

    public void updateValue(long roomId, long messageId){
        chatDisplayDao.updateValue(roomId, messageId);
    }


}
