package com.lodong.android.neighborcommunication.view.signup;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;

import java.lang.ref.WeakReference;

public class SignUpViewModel extends ViewModel {
    private final String TAG = SignUpViewModel.class.getSimpleName();
    private WeakReference<Activity> mRef;
    private Repository repository;

    public void setParent(Activity activity){
        this.mRef = new WeakReference<>(activity);
        init();
    }

    public void init(){
        repository = RepositoryImpl.getInstance();
    }

    public void signUp(String id, String password, String nickName){
        repository.signUp(id, password, nickName);
    }
}
