package com.lodong.android.neighborcommunication.view.chatroomlist;

import android.app.Activity;

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
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

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
        repository.getChatRoomList().observe((LifecycleOwner) mRef.get(), chatRoomDTOS -> {
            // chatRoomDiaplayInfo 객체 세팅
            settingChatRoomDisplayInfo(chatRoomDTOS);
        });
    }

    private void settingChatRoomDisplayInfo(List<ChatRoomDTO> chatRoomList) {
        if (chatRoomList != null) {
            chatRoomDisplayInfoList = new ArrayList<>();
            chatRoomDisplayInfoMutableLiveData.setValue(chatRoomDisplayInfoList);
            for (ChatRoomDTO chatRoomDTO : chatRoomList) {
                long chatRoomId = chatRoomDTO.getRoomId();

                repository.getChatMessage(chatRoomId).observe((LifecycleOwner) mRef.get(), chatMessageDTOS -> {
                    if (chatMessageDTOS != null) {
                        ChatMessageDTO lastMessageOb = chatMessageDTOS.get(chatMessageDTOS.size() - 1);
                        String receiverNickName = chatRoomDTO.getRoomUserOneNickName().equals(UserInfo.getInstance().getNickName())
                                ? chatRoomDTO.getRoomUserTwoNickName() : chatRoomDTO.getRoomUserOneNickName();
                        long roomId = chatRoomDTO.getRoomId();
                        String roomUserOne = chatRoomDTO.getRoomUserOneId();
                        String roomUserTwo = chatRoomDTO.getRoomUserTwoId();
                        String roomUserOneNickName = chatRoomDTO.getRoomUserOneNickName();
                        String roomUserTwoNickName = chatRoomDTO.getRoomUserTwoNickName();
                        String lastMessage = lastMessageOb.getMessage();
                        String lastMessageTime = lastMessageOb.getTimestamp();
                        String receiverId = chatRoomDTO.getRoomUserOneNickName().equals(UserInfo.getInstance().getNickName())
                                ? chatRoomDTO.getRoomUserTwoNickName() : chatRoomDTO.getRoomUserOneNickName();
                        ChatRoomDisplayInfo chatRoomDisplayInfo = new ChatRoomDisplayInfo(receiverNickName, roomId, roomUserOne,
                                roomUserTwo, roomUserOneNickName, roomUserTwoNickName, lastMessage, lastMessageTime, receiverId);
                        chatRoomDisplayInfoList.add(chatRoomDisplayInfo);
                    }
                });
            }

        }
    }

    public MutableLiveData<List<ChatRoomDisplayInfo>> getChatRoomDisplayInfoMutableLiveData() {
        return chatRoomDisplayInfoMutableLiveData;
    }
}
