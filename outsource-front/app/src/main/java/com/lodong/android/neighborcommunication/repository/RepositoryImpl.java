package com.lodong.android.neighborcommunication.repository;

import android.util.Log;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.login.LoginService;
import com.lodong.android.neighborcommunication.repository.memberlistservice.MemberListService;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;

public class RepositoryImpl implements Repository{
    private final String TAG = RepositoryImpl.class.getSimpleName();
    public static RepositoryImpl instance;
    private SignUpService signUpService;
    private LoginService loginService;
    private MemberListService memberListService;

    private RepositoryImpl(){
        signUpService = new SignUpService();
        loginService = new LoginService();
        memberListService = new MemberListService();
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
    public void getMemberList() {
        memberListService.getMemberList();
    }

    @Override
    public void getList() {

    }

}
