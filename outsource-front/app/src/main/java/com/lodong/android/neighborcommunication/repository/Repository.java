package com.lodong.android.neighborcommunication.repository;

import androidx.annotation.NonNull;

public interface Repository {
    public void getList();
    public void signUp(String id, String password, String nickName);
    public void login(String id, String password);
    public void getMemberList();
}
