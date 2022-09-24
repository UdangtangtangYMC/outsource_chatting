package com.lodong.android.neighborcommunication.repository.chatmessageservice;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.roomdb.RoomDB;

import java.util.List;

public class ChatMessageService {
    private ChatMessageDao chatMessageDao;
    private LiveData<List<ChatMessageDTO>> chatMessageList;

    public ChatMessageService(Application application){
        RoomDB db = RoomDB.getInstance(application);
        chatMessageDao = db.chatMessageDao();
    }

    public LiveData<List<ChatMessageDTO>> getChatMessageList() {
        return chatMessageList;
    }

    public LiveData<List<ChatMessageDTO>> getChatMessage(long chatRoomId){
        chatMessageList = chatMessageDao.getChatMessage(chatRoomId);
        return chatMessageList;
    }

    public void insert(ChatMessageDTO chatMessageDTO){
        chatMessageDao.insert(chatMessageDTO);
    }

    public void delete(ChatMessageDTO chatMessageDTO){
        chatMessageDao.delete(chatMessageDTO);
    }

    public ChatMessageDTO getChatMessageById(long chatId){
        return chatMessageDao.getChatMessageById(chatId);
    }


}
