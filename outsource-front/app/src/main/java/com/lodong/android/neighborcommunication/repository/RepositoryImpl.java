package com.lodong.android.neighborcommunication.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.ChatDisplayService.ChatDisplayService;
import com.lodong.android.neighborcommunication.repository.changestatusmessage.ChangeStatusMessageService;
import com.lodong.android.neighborcommunication.repository.chatmessageservice.ChatMessageService;
import com.lodong.android.neighborcommunication.repository.chatroomservice.ChatRoomService;
import com.lodong.android.neighborcommunication.repository.fcmservice.FCMTokenService;
import com.lodong.android.neighborcommunication.repository.login.LoginService;
import com.lodong.android.neighborcommunication.repository.memberlistservice.MemberListService;
import com.lodong.android.neighborcommunication.repository.model.ChatDisplayDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;
import com.lodong.android.neighborcommunication.utils.MainApplication;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;
import com.lodong.android.neighborcommunication.view.callback.NotificationCallBack;
import com.lodong.android.neighborcommunication.view.callback.RoomCreateCallBack;
import com.lodong.android.neighborcommunication.view.callback.SignUpCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserBlockedCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserUnblockedCallBack;

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
    private ChatDisplayService chatDisplayService;
    private UserBlockedCallBack blockedCallBack;
    private UserUnblockedCallBack unblockedCallBack;
    private SignUpCallBack signUpCallBack;

    private RepositoryImpl(){
        signUpService = new SignUpService();
        loginService = new LoginService();
        memberListService = new MemberListService();
        fcmTokenService = new FCMTokenService();
        changeStatusMessageService = new ChangeStatusMessageService();
        chatRoomService = new ChatRoomService(MainApplication.getInstance());
        chatMessageService = new ChatMessageService(MainApplication.getInstance());
        chatDisplayService = new ChatDisplayService(MainApplication.getInstance());
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
    public Long insertChatMessage(ChatMessageDTO chatMessageDTO) {
        return chatMessageService.insert(chatMessageDTO);
    }

    @Override
    public boolean isChatRoomExists(String p1, String p2) {
        return chatRoomService.isExists(p1, p2);
    }

    @Override
    public void createChatRoom(String id, String subject, ChatMessageDTO message) {
        chatRoomService.createNewChatRoom(id, subject, message);
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
    public void block(MemberDTO dto) {
        memberListService.block(dto, blockedCallBack);
    }

    @Override
    public void unblock(MemberDTO dto) {
        memberListService.unblock(dto, unblockedCallBack);
    }

    @Override
    public void enablePush() {
        loginService.enablePushNotification();
    }

    @Override
    public void disablePush() {
        loginService.disablePushNotification();
    }

    @Override
    public ChatMessageDTO getChatMessageById(long chatId) {
        return chatMessageService.getChatMessageById(chatId);
    }

    @Override
    public ChatRoomDTO getChatRoomById(long chatRoomId) {
        return chatRoomService.getChatRoomById(chatRoomId);
    }

    @Override
    public LiveData<List<ChatDisplayDTO>> getChatDisplayList() {
        return chatDisplayService.getAll();
    }

    @Override
    public void updateChatDisplay(long roomId, long messageId) {
        chatDisplayService.updateValue(roomId, messageId);
    }

    @Override
    public void insertChatDisplay(ChatDisplayDTO chatDisplayDTO) {
        chatDisplayService.insert(chatDisplayDTO);
    }

    @Override
    public ChatRoomDTO getChatRoom(String receiver) {
        return chatRoomService.getChatRoom(receiver);
    }

    @Override
    public void setGetMemberListCallBack(GetMemberListCallBack callBack) {
        memberListService.setGetMemberListCallBack(callBack);
    }

    @Override
    public void setGetLogInResultCallBack(GetLogInResultCallBack callBack) {
        loginService.setLoginResultCallBack(callBack);
    }

    @Override
    public void setRoomCreatedCallBack(RoomCreateCallBack callBack) {
        chatRoomService.setCallBack(callBack);
    }

    @Override
    public void setUserBlockedCallBack(UserBlockedCallBack callBack) {
        this.blockedCallBack = callBack;
    }

    @Override
    public void setUserUnblockedCallBack(UserUnblockedCallBack callBack) {
        this.unblockedCallBack = callBack;
    }

    @Override
    public void setSignUpCompleteCallBack(SignUpCallBack callBack) {
        signUpService.setCallBack(callBack);
    }

    @Override
    public void setNotificationCallBack(NotificationCallBack callBack) {
        loginService.setNotificationCallBack(callBack);
    }


    @Override
    public void getList() {

    }

}
