package com.lodong.android.neighborcommunication.repository;

import android.util.Log;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.changestatusmessage.ChangeStatusMessageService;
import com.lodong.android.neighborcommunication.repository.fcmservice.FCMTokenService;
import com.lodong.android.neighborcommunication.repository.login.LoginService;
import com.lodong.android.neighborcommunication.repository.memberlistservice.MemberListService;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;

public class RepositoryImpl implements Repository{
    private final String TAG = RepositoryImpl.class.getSimpleName();
    public static RepositoryImpl instance;
    private SignUpService signUpService;
    private LoginService loginService;
    private MemberListService memberListService;
    private FCMTokenService fcmTokenService;
    private ChangeStatusMessageService changeStatusMessageService;

    private RepositoryImpl(){
        signUpService = new SignUpService();
        loginService = new LoginService();
        memberListService = new MemberListService();
        fcmTokenService = new FCMTokenService();
        changeStatusMessageService = new ChangeStatusMessageService();
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
