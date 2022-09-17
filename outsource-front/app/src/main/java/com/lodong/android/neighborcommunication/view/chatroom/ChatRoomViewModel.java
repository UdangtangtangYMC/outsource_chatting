package com.lodong.android.neighborcommunication.view.chatroom;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.callback.RoomCreateCallBack;

import java.lang.ref.WeakReference;
import java.util.List;

public class ChatRoomViewModel extends ViewModel {
    private final String TAG = ChatRoomViewModel.class.getSimpleName();
    private Repository repository;
    private WeakReference<Activity> mRef;

    public void init(){
        repository = RepositoryImpl.getInstance();
    }

    public void setParent(Activity activity){
        this.mRef = new WeakReference<>(activity);
    }


    public void getChatMessageList() {
        long chatRoomId = 1;
        repository.getChatMessage(chatRoomId).observe((LifecycleOwner) mRef.get(),
                chatMessageDTOS -> Log.d(TAG, chatMessageDTOS.toString()));
    }
}
