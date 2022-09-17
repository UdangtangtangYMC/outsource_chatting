package com.lodong.android.neighborcommunication.repository;

import androidx.annotation.NonNull;

import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;

public interface Repository {
    public void getList();
    public void signUp(String id, String password, String nickName);
    public void login(String id, String password);
    public void getMemberList(String id);
    public void sendFcmToken(String id, String fcm);
    public void changeStatusMessage(String id, String newMessage);
    public void insertChatRoom(ChatRoomDTO chatRoomDTO);
    public void insertChatMessage(ChatMessageDTO chatMessageDTO);

    //settingCallBack
    public void setGetMemberListCallBack(GetMemberListCallBack callBack);
    public void setGetLogInResultCallBack(GetLogInResultCallBack callBack);
}
