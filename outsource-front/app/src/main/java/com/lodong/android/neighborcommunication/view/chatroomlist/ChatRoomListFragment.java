package com.lodong.android.neighborcommunication.view.chatroomlist;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.databinding.FragmentChatRoomListBinding;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.adapter.ChatRoomAdapter;

import java.util.List;

public class ChatRoomListFragment extends Fragment {
    private FragmentChatRoomListBinding binding;
    private ChatRoomListViewModel viewModel;
    private ChatRoomAdapter chatRoomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_room_list, container, false);
        viewModel = new ViewModelProvider(this).get(ChatRoomListViewModel.class);

        init();
        getChatRoomList();

        return binding.getRoot();
    }

    public void init(){
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        chatRoomAdapter = new ChatRoomAdapter();
        binding.recyclerview.setAdapter(chatRoomAdapter);
    }

    public void getChatRoomList(){
        viewModel.getChatRoomList().observe(this, chatRoomDTOS -> {
            chatRoomAdapter.changeMemberListAdapter(chatRoomDTOS);
        });
    }
}