package com.lodong.android.neighborcommunication.view.callback;

import com.lodong.android.neighborcommunication.repository.model.MemberDTO;

import java.util.List;

public interface GetLogInResultCallBack {
    public void onSuccess(MemberDTO member);
    public void onFailed(Throwable t);
}
