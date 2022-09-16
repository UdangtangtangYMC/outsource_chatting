package com.lodong.android.neighborcommunication.repository.signup;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpService {
    private final String TAG = SignUpService.class.getSimpleName();
    private RetrofitService retrofitService;

    public SignUpService(){
        retrofitService = RetrofitService.getInstance();
    }

    public void signUp(JsonObject jsonObject){
        retrofitService.getSignUpResult(jsonObject);
    }

}
