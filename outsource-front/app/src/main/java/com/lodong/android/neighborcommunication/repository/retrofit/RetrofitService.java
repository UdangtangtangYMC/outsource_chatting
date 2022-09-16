package com.lodong.android.neighborcommunication.repository.retrofit;

import android.util.Log;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.repository.model.SignUpRequestDTO;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
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

    public void getSignUpResult(JsonObject jsonObject) {
        retrofitServiceInterface.getSignUpResult(jsonObject).enqueue(new Callback<SignUpRequestDTO>() {
            @Override
            public void onResponse(Call<SignUpRequestDTO> call, Response<SignUpRequestDTO> response) {
                SignUpRequestDTO data = response.body();
                Log.d(TAG, "status code : " + data.getStatus());
            }

            @Override
            public void onFailure(Call<SignUpRequestDTO> call, Throwable t) {
                Log.e(TAG, "error : " + t.getMessage());
            }
        });
    }

    public void getLoginResult(JsonObject jsonObject) {
        retrofitServiceInterface.getLoginResult(jsonObject).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "login is success : " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void getMemberList() {
        retrofitServiceInterface.getMemberList().enqueue(new Callback<List<MemberDTO>>() {
            @Override
            public void onResponse(Call<List<MemberDTO>> call, Response<List<MemberDTO>> response) {
                for (MemberDTO memberDTO:response.body()) {
                    Log.d(TAG, memberDTO.toString());
                }
            }

            @Override
            public void onFailure(Call<List<MemberDTO>> call, Throwable t) {

            }
    });
}
}
