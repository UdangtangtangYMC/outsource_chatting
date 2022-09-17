package com.lodong.android.neighborcommunication.view.callback;

import com.lodong.android.neighborcommunication.repository.model.MemberDTO;

import java.util.List;

public interface GetMemberListCallBack {
    public void onSuccess(List<MemberDTO> memberList);
    public void onFailed(Throwable t);
}
