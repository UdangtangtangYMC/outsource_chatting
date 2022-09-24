package com.lodong.android.neighborcommunication.repository.login;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;

public class LoginService {
    private final String TAG = SignUpService.class.getSimpleName();
    private RetrofitService retrofitService;
    private GetLogInResultCallBack callBack;

    public LoginService(){
        retrofitService = RetrofitService.getInstance();
    }

    public void login(JsonObject jsonObject){
        retrofitService.getLoginResult(jsonObject, callBack);
    }
    public void setCallBack(GetLogInResultCallBack callBack) {
        this.callBack = callBack;
    }
}
