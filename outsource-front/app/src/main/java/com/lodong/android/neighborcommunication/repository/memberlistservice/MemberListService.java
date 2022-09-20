package com.lodong.android.neighborcommunication.repository.memberlistservice;

import com.google.gson.JsonObject;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.model.BlockDTO;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.repository.retrofit.RetrofitService;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserBlockedCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserUnblockedCallBack;

public class MemberListService {
    private final String TAG = MemberListService.class.getSimpleName();
    private RetrofitService retrofitService;
    private GetMemberListCallBack getMemberListCallBack;

    public MemberListService(){
        retrofitService = RetrofitService.getInstance();
    }

    public void getMemberList(JsonObject jsonObject){
        retrofitService.getMemberList(jsonObject, getMemberListCallBack);
    }

    public void block(MemberDTO dto, UserBlockedCallBack callBack){
        retrofitService.block(dto, callBack);
    }

    public void unblock(MemberDTO dto, UserUnblockedCallBack callBack) {
        retrofitService.unblock(dto, callBack);
    }

    public void setGetMemberListCallBack(GetMemberListCallBack getMemberListCallBack) {
        this.getMemberListCallBack = getMemberListCallBack;
    }
}
