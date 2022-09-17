package com.lodong.android.neighborcommunication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.databinding.ActivityMainBinding;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
        settingLoginUserInfo();
    }

    private void init(){
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.getNavController());


    }
    private void settingLoginUserInfo(){
        String id = PreferenceManager.getId(this);
        String statusMessage = PreferenceManager.getStatusMessage(this);
        String nickName = PreferenceManager.getNickName(this);

        UserInfo userInfo = UserInfo.getInstance();
        userInfo.setId(id);
        userInfo.setMessage(statusMessage);
        userInfo.setNickName(nickName);
    }
}