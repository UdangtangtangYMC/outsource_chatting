package com.lodong.android.neighborcommunication.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.changestatusmessage.ChangeStatusMessageService;
import com.lodong.android.neighborcommunication.repository.chatmessageservice.ChatMessageService;
import com.lodong.android.neighborcommunication.repository.chatroomservice.ChatRoomService;
import com.lodong.android.neighborcommunication.repository.fcmservice.FCMTokenService;
import com.lodong.android.neighborcommunication.repository.login.LoginService;
import com.lodong.android.neighborcommunication.repository.memberlistservice.MemberListService;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;
import com.lodong.android.neighborcommunication.utils.MainApplication;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;

import java.util.List;

public class RepositoryImpl implements Repository {
    private final String TAG = RepositoryImpl.class.getSimpleName();
    public static RepositoryImpl instance;
    private SignUpService signUpService;
    private LoginService loginService;
    private MemberListService memberListService;
    private FCMTokenService fcmTokenService;
    private ChangeStatusMessageService changeStatusMessageService;
    private ChatRoomService chatRoomService;
    private ChatMessageService chatMessageService;

    private RepositoryImpl(){
        signUpService = new SignUpService();
        loginService = new LoginService();
        memberListService = new MemberListService();
        fcmTokenService = new FCMTokenService();
        changeStatusMessageService = new ChangeStatusMessageService();
        chatRoomService = new ChatRoomService(MainApplication.getInstance());
        chatMessageService = new ChatMessageService(MainApplication.getInstance());
    }

    public static RepositoryImpl getInstance(){
        if(instance == null){
            instance = new RepositoryImpl();
        }
        return instance;
    }

    @Override
    public void signUp(String id, String password, String nickName) {
        Log.d(TAG, "signUp");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("nickName", nickName);
        signUpService.signUp(jsonObject);
    }

    @Override
    public void login(String id, String password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("password", password);
        loginService.login(jsonObject);
    }

    @Override
    public void getMemberList(String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        memberListService.getMemberList(jsonObject);
    }

    @Override
    public void sendFcmToken(String id, String fcmToken) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("fcmToken", fcmToken);
        fcmTokenService.sendFcmToken(jsonObject);

    }

    @Override
    public void changeStatusMessage(String id, String newMessage) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("newMessage", newMessage);
        changeStatusMessageService.sendFcmToken(jsonObject);
    }

    @Override
    public void insertChatRoom(ChatRoomDTO chatRoomDTO) {
        chatRoomService.insert(chatRoomDTO);
    }
    @Override
    public void insertChatMessage(ChatMessageDTO chatMessageDTO) {
        chatMessageService.insert(chatMessageDTO);
    }

    @Override
    public boolean isChatRoomExists(String p1, String p2) {
        return chatRoomService.isExists(p1, p2);
    }

    @Override
    public LiveData<List<ChatMessageDTO>> getChatMessage(long id) {
        return chatMessageService.getChatMessage(id);
    }

    @Override
    public LiveData<List<ChatRoomDTO>> getChatRoomList() {
        return chatRoomService.getChatRoomList();
    }

    @Override
    public void setGetMemberListCallBack(GetMemberListCallBack callBack) {
        memberListService.setGetMemberListCallBack(callBack);
    }

    @Override
    public void setGetLogInResultCallBack(GetLogInResultCallBack callBack) {
        loginService.setCallBack(callBack);
    }


    @Override
    public void getList() {

    }

}
