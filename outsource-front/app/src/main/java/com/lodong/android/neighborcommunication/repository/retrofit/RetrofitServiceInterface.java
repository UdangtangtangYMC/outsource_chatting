package com.lodong.android.neighborcommunication.repository.retrofit;


import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.model.BlockDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.repository.model.SignUpRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitServiceInterface {

    @POST("/api/v1/auth/registration")
    Call<SignUpRequestDTO> getSignUpResult(@Body JsonObject jsonObject);

    @FormUrlEncoded
    @POST("/api/v1/auth/do")
    Call<MemberDTO> getLoginResult(@Field("id")String id, @Field("password") String password);

    @POST("/api/v1/user/getUsers")
    Call<List<MemberDTO>> getMemberList(@Body JsonObject jsonObject);

    @POST("api/v1/auth/fcm")
    Call<String> sendFcmToken(@Body JsonObject jsonObject);

    @POST("api/v1/user/setProfileMessage")
    Call<String> changeStatusMessage(@Body JsonObject jsonObject);

    @POST("/api/v1/chat/new")
    Call<ChatRoomDTO> createNewChatRoom(@Body JsonObject jsonObject);

    @POST("/api/v1/user/block")
    Call<Void> block(@Body BlockDTO blockDTO);

    @POST("/api/v1/user/unblock")
    Call<Void> unblock(@Body BlockDTO blockDTO);

    @POST("/api/v1/user/notification/on")
    Call<Void> enablePushNotification(@Body JsonObject json);

    @POST("/api/v1/user/notification/off")
    Call<Void> disablePushNotification(@Body JsonObject json);
}
