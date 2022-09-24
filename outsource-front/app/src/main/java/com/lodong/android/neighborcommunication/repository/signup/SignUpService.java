package com.lodong.android.neighborcommunication.repository.signup;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;
import com.lodong.android.neighborcommunication.view.callback.SignUpCallBack;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpService {
    private final String TAG = SignUpService.class.getSimpleName();
    private RetrofitService retrofitService;
    private SignUpCallBack callBack;

    public SignUpService(){
        retrofitService = RetrofitService.getInstance();
    }

    public void setCallBack(SignUpCallBack callBack) {
        this.callBack = callBack;
    }

    public void signUp(JsonObject jsonObject){
        retrofitService.getSignUpResult(jsonObject, callBack);
    }



}
