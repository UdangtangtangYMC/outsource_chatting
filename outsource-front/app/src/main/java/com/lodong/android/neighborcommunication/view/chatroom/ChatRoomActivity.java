package com.lodong.android.neighborcommunication.view.chatroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.databinding.ActivityChatRoomBinding;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.view.adapter.ChattingAdapter;

import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {
    private final String TAG = ChatRoomActivity.class.getSimpleName();
    private ChatRoomViewModel viewModel;
    private ActivityChatRoomBinding binding;
    private ChattingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_room);
        binding.setActivity(this);
        viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(ChatRoomViewModel.class);
        viewModel.setParent(this);
        binding.setViewModel(viewModel);
        viewModel.init();

        init();
        setLiveData();

        viewModel.getChatRoomId();
    }

    private void init() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        adapter = new ChattingAdapter();
        binding.recyclerview.setAdapter(adapter);
    }

    private void setLiveData() {
        viewModel.getChatRoomIdML().observe(this, chatRoomId -> {
            Log.d(TAG, "chatRoomId : " + chatRoomId);
            if (chatRoomId != -99) {
                getChatMessageList(chatRoomId);
            }
        });
    }

    public void sendMessage() {
        Log.d(TAG, "sendMessage");
        String message = binding.edtMessage.getText().toString();
        if(message.isEmpty()) return;
        viewModel.sendMessage(message);
        binding.edtMessage.setText("");
    }

    public void openDrawer() {
        binding.drawer.openDrawer(GravityCompat.END);
    }

    private void getChatMessageList(long chatRoomId) {
        viewModel.getChatMessageList(chatRoomId).observe(this, chatMessageDTOS
                -> {
            Log.d(TAG, "chatMessageCount : " + chatMessageDTOS.size());
            adapter.setChatMessageList(chatMessageDTOS);
            adapter.setNickName(viewModel.getReceiverNickName().get());
            binding.recyclerview.scrollToPosition(chatMessageDTOS.size() - 1);
        });
    }
}