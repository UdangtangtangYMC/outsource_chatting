package com.lodong.android.neighborcommunication.repository.retrofit;


import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.repository.model.SignUpRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitServiceInterface {

    @POST("/api/v1/auth/registration")
    Call<SignUpRequestDTO> getSignUpResult(@Body JsonObject jsonObject);

    @POST("/api/v1/auth/do")
    Call<MemberDTO> getLoginResult(@Body JsonObject jsonObject);

    @POST("/api/v1/user/getUsers")
    Call<List<MemberDTO>> getMemberList(@Body JsonObject jsonObject);

    @POST("api/v1/auth/fcm")
    Call<String> sendFcmToken(@Body JsonObject jsonObject);

    @POST("api/v1/user/setProfileMessage")
    Call<String> changeStatusMessage(@Body JsonObject jsonObject);

}
