package com.lodong.android.neighborcommunication.repository.chatroomservice;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.model.ChatMessage;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;
import com.lodong.android.neighborcommunication.repository.roomdb.RoomDB;
import com.lodong.android.neighborcommunication.view.callback.RoomCreateCallBack;

import java.util.List;

public class ChatRoomService {
    private ChatRoomDao chatRoomDao;
    private LiveData<List<ChatRoomDTO>> chatRoomList;
    private final RetrofitService retrofitService;
    private RoomCreateCallBack callBack;

    public ChatRoomService(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        chatRoomDao = db.chatRoomDao();
        chatRoomList = chatRoomDao.getAll();
        this.retrofitService = RetrofitService.getInstance();
    }

    public LiveData<List<ChatRoomDTO>> getChatRoomList(){
        chatRoomList = chatRoomDao.getAll();
        return chatRoomList;
    }

    public void createNewChatRoom(String id, String subject, ChatMessageDTO message){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("subject", subject);
        retrofitService.newChatRoom(jsonObject, message, callBack);
    }

    public boolean reject(String target) {
        return true;
    }

    public void insert(ChatRoomDTO chatRoomDTO){
        chatRoomDao.insert(chatRoomDTO);
    }

    public void delete(ChatRoomDTO chatRoomDTO) {
        chatRoomDao.delete(chatRoomDTO);
    }

    public ChatRoomDTO getChatRoom(String receiver){
        return chatRoomDao.getChatRoom(receiver);
    }

    public void setCallBack(RoomCreateCallBack callBack) {
        this.callBack = callBack;
    }

    public boolean isExists(String p1, String p2) {
        return chatRoomDao.existsByParticipants(p1, p2);
    }

    public ChatRoomDTO getChatRoomById(long id){
        return chatRoomDao.getChatRoomById(id);
    }
}
