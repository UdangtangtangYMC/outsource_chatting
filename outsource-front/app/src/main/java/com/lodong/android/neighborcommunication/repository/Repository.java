package com.lodong.android.neighborcommunication.repository;

import androidx.lifecycle.LiveData;

import com.lodong.android.neighborcommunication.repository.model.ChatMessage;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;
import com.lodong.android.neighborcommunication.view.callback.RoomCreateCallBack;

import java.util.List;

public interface Repository {
    public void getList();
    public void signUp(String id, String password, String nickName);
    public void login(String id, String password);
    public void getMemberList(String id);
    public void sendFcmToken(String id, String fcm);
    public void changeStatusMessage(String id, String newMessage);
    public void insertChatRoom(ChatRoomDTO chatRoomDTO);
    public void insertChatMessage(ChatMessageDTO chatMessageDTO);
    public boolean isChatRoomExists(String p1, String p2);
    public void createChatRoom(String id, String subject, ChatMessage message);
    public LiveData<List<ChatMessageDTO>> getChatMessage(long id);
    public LiveData<List<ChatRoomDTO>> getChatRoomList();

    //settingCallBack
    public void setGetMemberListCallBack(GetMemberListCallBack callBack);
    public void setGetLogInResultCallBack(GetLogInResultCallBack callBack);
    public void setRoomCreatedCallBack(RoomCreateCallBack callBack);
}
