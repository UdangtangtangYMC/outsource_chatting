package com.lodong.android.neighborcommunication.view.chatroom;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;
import com.lodong.android.neighborcommunication.view.callback.GetChatRoomIdCallBack;
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
    private String userId;
    public ObservableField<String> receiverNickName = new ObservableField<>();

    private MutableLiveData<Long> chatRoomIdML = new MutableLiveData<>();

    public void setParent(Activity activity) {
        this.mRef = new WeakReference<>(activity);
    }

    public void init() {
        repository = RepositoryImpl.getInstance();
        if(!StompUtils.INSTANCE.isConnect()){
            Log.d(TAG, "stomp is disconnect");
            StompUtils.init(mRef.get());
        }
        userId = UserInfo.getInstance().getId() == null ? PreferenceManager.getId(mRef.get()) : UserInfo.getInstance().getId();
    }

    public void getChatRoomId(){
        Intent intent = mRef.get().getIntent();
        chatRoomid = intent.getLongExtra("chatRoomId", noChatRoomId);
        receiver = intent.getStringExtra("receiver");
        this.receiverNickName.set(intent.getStringExtra("receiverNickName"));
        Log.d(TAG, "chatRoomid : " + chatRoomid);
        Log.d(TAG, "receiver : " + receiver);
        Log.d(TAG, "receiverNickName " + receiverNickName.get());
        chatRoomIdML.setValue(chatRoomid);
    }

    public void sendMessage(String message) {
        ChatMessageDTO chatMessage = new ChatMessageDTO(chatRoomid, userId, receiver, message);
        Log.d(TAG, "send message info : " + chatMessage);
        StompUtils.INSTANCE.send(chatMessage, getChatRoomIdCallBack());
    }

    public LiveData<List<ChatMessageDTO>> getChatMessageList(long chatRoomId) {
        return repository.getChatMessage(chatRoomId);
    }

    public MutableLiveData<Long> getChatRoomIdML() {
        return chatRoomIdML;
    }


    private GetChatRoomIdCallBack getChatRoomIdCallBack(){
        return chatRoomId -> changeId(chatRoomId);
    }

    public void changeId(long id){
        Log.d(TAG, "changeId");
        chatRoomid = id;
        chatRoomIdML.setValue(chatRoomid);
    }

    public ObservableField<String> getReceiverNickName() {
        return receiverNickName;
    }
}
