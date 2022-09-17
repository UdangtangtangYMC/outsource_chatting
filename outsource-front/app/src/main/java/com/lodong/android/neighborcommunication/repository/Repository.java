package com.lodong.android.neighborcommunication.repository;

import androidx.annotation.NonNull;

import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;

public interface Repository {
    public void getList();
    public void signUp(String id, String password, String nickName);
    public void login(String id, String password);
    public void getMemberList();

    //settingCallBack
    public void setGetMemberListCallBack(GetMemberListCallBack callBack);
}
