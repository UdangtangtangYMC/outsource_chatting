package com.lodong.android.neighborcommunication.view.callback;

import com.lodong.android.neighborcommunication.repository.model.ChatMessage;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;

public interface RoomCreateCallBack {
    public void onSuccess(ChatRoomDTO chatRoom, ChatMessageDTO message);
    public void onFailed(ChatRoomDTO chatRoom, ChatMessageDTO message);
}
