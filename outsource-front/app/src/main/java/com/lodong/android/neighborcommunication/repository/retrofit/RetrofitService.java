package com.lodong.android.neighborcommunication.repository.retrofit;

import android.util.Log;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.model.BlockDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.repository.model.SignUpRequestDTO;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;
import com.lodong.android.neighborcommunication.view.callback.RoomCreateCallBack;
import com.lodong.android.neighborcommunication.view.callback.SignUpCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserBlockedCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserUnblockedCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static RetrofitService instance;
    private final String TAG = RetrofitService.class.getSimpleName();
    private final String base_url = "http://49.174.169.48:13883";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(new NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetrofitServiceInterface retrofitServiceInterface = retrofit.create(RetrofitServiceInterface.class);

    private RetrofitService() {
    }

    public static RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    public void getSignUpResult(JsonObject jsonObject, SignUpCallBack callBack) {
        retrofitServiceInterface.getSignUpResult(jsonObject).enqueue(new Callback<SignUpRequestDTO>() {
            @Override
            public void onResponse(Call<SignUpRequestDTO> call, Response<SignUpRequestDTO> response) {
                SignUpRequestDTO data = response.body();
                if(response.code() == 200) callBack.onSuccess();
                else callBack.onFailed();
            }

            @Override
            public void onFailure(Call<SignUpRequestDTO> call, Throwable t) {
                Log.e(TAG, "error : " + t.getMessage());
            }
        });
    }

    public void getLoginResult(JsonObject jsonObject, GetLogInResultCallBack callBack) {
        retrofitServiceInterface.getLoginResult(jsonObject).enqueue(new Callback<MemberDTO>() {
            @Override
            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                Log.d(TAG, "login is success : " + response.isSuccessful());
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void getMemberList(JsonObject jsonObject, GetMemberListCallBack callBack) {
        retrofitServiceInterface.getMemberList(jsonObject).enqueue(new Callback<List<MemberDTO>>() {
            @Override
            public void onResponse(Call<List<MemberDTO>> call, Response<List<MemberDTO>> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<MemberDTO>> call, Throwable t) {

            }
        });
    }

    public void sendFcmToken(JsonObject jsonObject){
        retrofitServiceInterface.sendFcmToken(jsonObject).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                /*Log.d(TAG, response.body());*/
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void changeStatusMessage(JsonObject jsonObject){
        retrofitServiceInterface.changeStatusMessage(jsonObject).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void newChatRoom(JsonObject roomDTO, ChatMessageDTO message, RoomCreateCallBack callBack) {
        retrofitServiceInterface.createNewChatRoom(roomDTO).enqueue(new Callback<ChatRoomDTO>() {
            @Override
            public void onResponse(Call<ChatRoomDTO> call, Response<ChatRoomDTO> response) {
                if(response.code() == 200) callBack.onSuccess(response.body(), message);
                else if (response.code() == 400) {
                    Log.e(TAG, response.errorBody().toString());
                    callBack.onFailed(response.body(), message);
                }
            }

            @Override
            public void onFailure(Call<ChatRoomDTO> call, Throwable t) {

            }
        });
    }

    public void block(MemberDTO dto, UserBlockedCallBack callBack) {
        retrofitServiceInterface.block(new BlockDTO(UserInfo.getInstance().getId(), dto.getId())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callBack.onSuccess(dto);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void unblock(MemberDTO dto, UserUnblockedCallBack callBack) {
        retrofitServiceInterface.unblock(new BlockDTO(UserInfo.getInstance().getId(), dto.getId())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callBack.onSuccess(dto);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
