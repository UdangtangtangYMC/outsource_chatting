package com.lodong.android.neighborcommunication.view.chatroomlist;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;

import java.lang.ref.WeakReference;
import java.util.List;

public class ChatRoomListViewModel extends ViewModel {
    private final String TAG = ChatRoomListViewModel.class.getSimpleName();
    private WeakReference<Activity> mRef;
    private Repository repository;

    public void setParent(Activity activity){
        mRef = new WeakReference<>(activity);
        repository = RepositoryImpl.getInstance();
    }

    public LiveData<List<ChatRoomDTO>> getChatRoomList(){
        return repository.getChatRoomList();
    }
}
