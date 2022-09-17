package com.lodong.android.neighborcommunication.view.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;
import com.lodong.android.neighborcommunication.view.MainActivity;
import com.lodong.android.neighborcommunication.view.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (isLoggedIn()) startMain();
        else startLogin();
    }

    private boolean isLoggedIn() {
        return PreferenceManager.isLogin(this);
    }

    private void startMain() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }

    private void startLogin() {
        Intent logIn = new Intent(this, LoginActivity.class);
        startActivity(logIn);
        finish();
    }
}