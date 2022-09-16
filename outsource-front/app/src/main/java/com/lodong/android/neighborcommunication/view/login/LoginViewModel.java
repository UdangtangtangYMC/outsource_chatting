package com.lodong.android.neighborcommunication.view.login;


import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;

import java.lang.ref.WeakReference;

public class LoginViewModel extends ViewModel {
    private WeakReference<Activity> mRef;
    private Repository repository;

    public LoginViewModel(){}

    public void setParent(Activity activity){
        this.mRef = new WeakReference<>(activity);
        repository = RepositoryImpl.getInstance();
    }

    public void login(String id, String password){
        repository.login(id, password);
    }
}
