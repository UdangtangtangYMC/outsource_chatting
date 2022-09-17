package com.lodong.android.neighborcommunication.repository.memberlistservice;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;
import com.lodong.android.neighborcommunication.repository.signup.SignUpService;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;

public class MemberListService {
    private final String TAG = MemberListService.class.getSimpleName();
    private RetrofitService retrofitService;
    private GetMemberListCallBack getMemberListCallBack;

    public MemberListService(){
        retrofitService = RetrofitService.getInstance();
    }

    public void getMemberList(){
        retrofitService.getMemberList(getMemberListCallBack);
    }

    public void setGetMemberListCallBack(GetMemberListCallBack getMemberListCallBack) {
        this.getMemberListCallBack = getMemberListCallBack;
    }
}
