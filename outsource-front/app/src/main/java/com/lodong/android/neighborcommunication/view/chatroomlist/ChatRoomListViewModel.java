package com.lodong.android.neighborcommunication.view.chatroomlist;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.room.MapInfo;

import com.lodong.android.neighborcommunication.data.ChatRoomDisplayInfo;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatDisplayDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoomListViewModel extends ViewModel {
    private final String TAG = ChatRoomListViewModel.class.getSimpleName();
    private WeakReference<Activity> mRef;
    private Repository repository;
    private List<ChatRoomDisplayInfo> chatRoomDisplayInfoList;
    private MutableLiveData<List<ChatRoomDisplayInfo>> chatRoomDisplayInfoMutableLiveData = new MutableLiveData<>();

    public void setParent(Activity activity) {
        mRef = new WeakReference<>(activity);
        repository = RepositoryImpl.getInstance();
    }

    public void getChatRoomList() {
        //채팅방 감시 및 라스트메시지 정보 삽입.
        //라스트 메시지의 경우 서버에서 메시지가 도착하면 데이터 베이스에 삽입된다.
        //디비의 채팅메시지 리스트를 관찰 중인 viewModel 에서 이를 해당 채팅방 정보를
        //가지고있는 객체에 갱신한다.
        // chatRoomDiaplayInfo 객체 세팅
        repository.getChatDisplayList().observe((LifecycleOwner) mRef.get(), this::settingChatRoomDisplayInfo);
    }

    private void settingChatRoomDisplayInfo(List<ChatDisplayDTO> chatDisplayDTOS) {
        Log.d(TAG, "쌉 미얀마");
        chatRoomDisplayInfoList = new ArrayList<>();
        for(ChatDisplayDTO chatDisplayDTO:chatDisplayDTOS){
            long chatRoomId = chatDisplayDTO.getChatRoomId();
            long messageId = chatDisplayDTO.getMessageId();
            ChatMessageDTO chatMessageDTO = repository.getChatMessageById(messageId);
            ChatRoomDTO chatRoomDTO = repository.getChatRoomById(chatRoomId);

            String receiver = chatRoomDTO.getRoomUserOneId().equals(UserInfo.getInstance().getId())
                    ? chatRoomDTO.getRoomUserTwoId() : chatRoomDTO.getRoomUserOneId();
            String receiverName = chatRoomDTO.getRoomUserOneNickName().equals(UserInfo.getInstance().getId())
                    ? chatRoomDTO.getRoomUserTwoNickName() : chatRoomDTO.getRoomUserOneNickName();
            String lastMessage = chatMessageDTO.getMessage();
            String lastMessageTime = chatMessageDTO.getTimestamp();
            ChatRoomDisplayInfo chatRoomDisplayInfo = new ChatRoomDisplayInfo(receiverName, receiver, chatRoomId, lastMessage, lastMessageTime);
            Log.d(TAG, chatRoomDisplayInfo.toString());
            chatRoomDisplayInfoList.add(chatRoomDisplayInfo);
        }
        this.chatRoomDisplayInfoMutableLiveData.setValue(chatRoomDisplayInfoList);

    }

    public MutableLiveData<List<ChatRoomDisplayInfo>> getChatRoomDisplayInfoMutableLiveData() {
        return chatRoomDisplayInfoMutableLiveData;
    }
}
