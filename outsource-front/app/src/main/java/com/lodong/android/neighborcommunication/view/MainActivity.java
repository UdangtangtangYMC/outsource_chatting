package com.lodong.android.neighborcommunication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.databinding.ActivityMainBinding;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatMessage;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.utils.MainApplication;
import com.lodong.android.neighborcommunication.utils.preferences.FCMManager;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;
import com.lodong.android.neighborcommunication.utils.stomp.StompUtils;
import com.lodong.android.neighborcommunication.view.callback.GetFCMToken;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
        settingLoginUserInfo();
        getNowFCMToken();
    }

    private void init() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.getNavController());
        repository = RepositoryImpl.getInstance();
        MainApplication.getInstance().registerBroadCast();
    }

    private void settingLoginUserInfo() {
        String id = PreferenceManager.getId(this);
        String statusMessage = PreferenceManager.getStatusMessage(this);
        String nickName = PreferenceManager.getNickName(this);

        UserInfo userInfo = UserInfo.getInstance();
        userInfo.setId(id);
        userInfo.setMessage(statusMessage);
        userInfo.setNickName(nickName);
    }

    private void getNowFCMToken() {
        FCMManager.getNowFCMToken(getFCMToken());
    }

    public GetFCMToken getFCMToken() {
        return new GetFCMToken() {
            @Override
            public void onSuccess(String fcmToken) {
                sendFCMToken(fcmToken);
            }

            @Override
            public void onFailed(Exception e) {

            }
        };
    }

    private void sendFCMToken(String fcmToken) {
        repository.sendFcmToken(UserInfo.getInstance().getId(), fcmToken);
    }



}