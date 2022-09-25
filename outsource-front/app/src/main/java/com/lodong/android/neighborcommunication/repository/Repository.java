package com.lodong.android.neighborcommunication.repository;

import androidx.lifecycle.LiveData;

import com.lodong.android.neighborcommunication.repository.model.ChatDisplayDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatMessage;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.view.callback.GetLogInResultCallBack;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;
import com.lodong.android.neighborcommunication.view.callback.NotificationCallBack;
import com.lodong.android.neighborcommunication.view.callback.RoomCreateCallBack;
import com.lodong.android.neighborcommunication.view.callback.SignUpCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserBlockedCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserUnblockedCallBack;

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
    public void createChatRoom(String id, String subject, ChatMessageDTO message);
    public ChatRoomDTO getChatRoom(String receiver);
    public LiveData<List<ChatMessageDTO>> getChatMessage(long id);
    public LiveData<List<ChatRoomDTO>> getChatRoomList();
    public LiveData<List<ChatDisplayDTO>> getChatDisplayList();
    public void updateChatDisplay(long roomId, long messageId);
    public void insertChatDisplay(ChatDisplayDTO chatDisplayDTO);
    public void block(MemberDTO dto);
    public void unblock(MemberDTO dto);
    void enablePush();
    void disablePush();
    public ChatMessageDTO getChatMessageById(long chatId);
    public ChatRoomDTO getChatRoomById(long chatRoomId);

    //settingCallBack
    public void setGetMemberListCallBack(GetMemberListCallBack callBack);
    public void setGetLogInResultCallBack(GetLogInResultCallBack callBack);
    public void setRoomCreatedCallBack(RoomCreateCallBack callBack);
    public void setUserBlockedCallBack(UserBlockedCallBack callBack);
    public void setUserUnblockedCallBack(UserUnblockedCallBack callBack);
    void setSignUpCompleteCallBack(SignUpCallBack callBack);

    void setNotificationCallBack(NotificationCallBack callBack);
}