package com.lodong.android.neighborcommunication.view.callback;

import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;

public interface UserBlockedCallBack {
    public void onSuccess(MemberDTO memberDTO);
    public void onFailed(Throwable t);
}
