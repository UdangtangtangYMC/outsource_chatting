package com.lodong.android.neighborcommunication.view.login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;
import com.lodong.android.neighborcommunication.view.MainActivity;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;

import java.lang.ref.WeakReference;

public class LoginViewModel extends ViewModel {

    private final String TAG = LoginViewModel.class.getSimpleName();
    private WeakReference<Activity> mRef;
    private Repository repository;

    public LoginViewModel(){}

    public void setParent(Activity activity){
        this.mRef = new WeakReference<>(activity);
        repository = RepositoryImpl.getInstance();
        repository.setGetLogInResultCallBack(getLogInResultCallBack());
    }

    public void login(String id, String password){
        repository.login(id, password);
    }

    public GetLogInResultCallBack getLogInResultCallBack(){
        return new GetLogInResultCallBack() {
            @Override
            public void onSuccess(MemberDTO member) {
                if(member == null){
                    Toast.makeText(mRef.get(), "아이디 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, member.toString());
                Context context = mRef.get().getApplicationContext();
                PreferenceManager.setId(context, member.getId());
                PreferenceManager.setNickName(context, member.getNickName());
                PreferenceManager.setStatusMessage(context, member.getMessage());
                PreferenceManager.setIsLogin(context, true);
                PreferenceManager.setNotificationEnabled(context, member.isReceiveNotification());

                Intent intent = new Intent(context, MainActivity.class);
                mRef.get().startActivity(intent);
                mRef.get().finish();
            }

            @Override
            public void onFailed(Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        };
    }


}
