package com.lodong.android.neighborcommunication.view.callback;

import com.lodong.android.neighborcommunication.repository.model.MemberDTO;

public interface UserUnblockedCallBack {
    public void onSuccess(MemberDTO memberDTO);
    public void onFailed(Throwable t);
}
