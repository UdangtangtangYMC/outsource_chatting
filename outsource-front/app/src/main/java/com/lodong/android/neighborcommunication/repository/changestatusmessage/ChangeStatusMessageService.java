package com.lodong.android.neighborcommunication.repository.changestatusmessage;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;

public class ChangeStatusMessageService {
    private final String TAG = SignUpService.class.getSimpleName();
    private RetrofitService retrofitService;

    public ChangeStatusMessageService(){
        retrofitService = RetrofitService.getInstance();
    }

    public void sendFcmToken(JsonObject jsonObject){
        retrofitService.changeStatusMessage(jsonObject);
    }
}
