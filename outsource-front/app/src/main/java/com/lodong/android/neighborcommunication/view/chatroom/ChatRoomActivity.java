package com.lodong.android.neighborcommunication.view.chatroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.util.Log;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.databinding.ActivityChatRoomBinding;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;

import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {
    private final String TAG = ChatRoomActivity.class.getSimpleName();
    private ChatRoomViewModel viewModel;
    private ActivityChatRoomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_room);
        viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(ChatRoomViewModel.class);

        viewModel.init();

        init();

        getChatMessageList();
    }

    private void init(){

    }

    private void getChatMessageList(){
        viewModel.getChatMessageList();
    }
}