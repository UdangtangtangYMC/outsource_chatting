package com.lodong.android.neighborcommunication.view.chatroomlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.ChatRoomDisplayInfo;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.databinding.FragmentChatRoomListBinding;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.adapter.ChatRoomAdapter;
import com.lodong.android.neighborcommunication.view.chatroom.ChatRoomActivity;

import java.util.List;

public class ChatRoomListFragment extends Fragment {
    private final String TAG = ChatRoomListFragment.class.getSimpleName();
    private FragmentChatRoomListBinding binding;
    private ChatRoomListViewModel viewModel;
    private ChatRoomAdapter chatRoomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_room_list, container, false);
        viewModel = new ViewModelProvider(this).get(ChatRoomListViewModel.class);
        viewModel.setParent(getActivity());
        init();
        getChatRoomList();

        return binding.getRoot();
    }

    public void init(){
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        chatRoomAdapter = new ChatRoomAdapter();
        chatRoomAdapter.setOnChatRoomClickListener(onChatRoomClickListener());
        binding.recyclerview.setAdapter(chatRoomAdapter);
    }

    public void getChatRoomList(){
        viewModel.getChatRoomDisplayInfoMutableLiveData().observe(getActivity(), chatRoomDisplayInfos -> {
            chatRoomAdapter.changeMemberListAdapter(chatRoomDisplayInfos);
        });
        viewModel.getChatRoomList();
    }

    public OnChatRoomClickListener onChatRoomClickListener(){
        return chatRoomDTO -> {
            long id = chatRoomDTO.getRoomId();
            String receiver = chatRoomDTO.getReceiver();
            String receiverNickName = chatRoomDTO.getReceiverNickName();
            intentChatRoomActivity(id, receiver, receiverNickName);
        };
    }

    private void intentChatRoomActivity(long id, String receiver, String receiverNickName){
        Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
        intent.putExtra("chatRoomId", id);
        intent.putExtra("receiver", receiver);
        intent.putExtra("receiverNickName", receiverNickName);
        startActivity(intent);
    }

    public interface OnChatRoomClickListener{
        public void onClick(ChatRoomDisplayInfo chatRoomDisplayInfo);
    }
}