package com.lodong.android.neighborcommunication.view.chatroom;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatMessage;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.callback.RoomCreateCallBack;
import com.lodong.android.neighborcommunication.utils.stomp.StompUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class ChatRoomViewModel extends ViewModel {
    private final String TAG = ChatRoomViewModel.class.getSimpleName();
    private Repository repository;
    private WeakReference<Activity> mRef;

    private long chatRoomid;
    private final long noChatRoomId = -99;
    private String receiver;

    private MutableLiveData<Long> chatRoomIdML = new MutableLiveData<>();

    public void setParent(Activity activity) {
        this.mRef = new WeakReference<>(activity);
    }

    public void init() {
        repository = RepositoryImpl.getInstance();
    }

    public void getChatRoomId(){
        Intent intent = mRef.get().getIntent();
        chatRoomid = intent.getLongExtra("chatRoomId", noChatRoomId);
        receiver = intent.getStringExtra("receiver");
        chatRoomIdML.setValue(chatRoomid);
    }

    public void sendMessage(String message){
        ChatMessage chatMessage = new ChatMessage(chatRoomid, UserInfo.getInstance().getId(), receiver, message);
        StompUtils.INSTANCE.send(chatMessage);
    }

    public LiveData<List<ChatMessageDTO>> getChatMessageList(long chatRoomId) {
        return repository.getChatMessage(chatRoomId);
    }

    public MutableLiveData<Long> getChatRoomIdML() {
        return chatRoomIdML;
    }
}
