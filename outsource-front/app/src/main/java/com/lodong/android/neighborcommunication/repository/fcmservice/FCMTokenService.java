package com.lodong.android.neighborcommunication.repository.fcmservice;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;

public class FCMTokenService {
    private final String TAG = SignUpService.class.getSimpleName();
    private RetrofitService retrofitService;

    public FCMTokenService(){
        retrofitService = RetrofitService.getInstance();
    }

    public void sendFcmToken(JsonObject jsonObject){
        retrofitService.sendFcmToken(jsonObject);
    }
}
