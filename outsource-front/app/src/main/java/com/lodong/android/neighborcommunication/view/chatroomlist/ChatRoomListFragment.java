package com.lodong.android.neighborcommunication.view.chatroomlist;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.databinding.FragmentChatRoomListBinding;

public class ChatRoomListFragment extends Fragment {
    private FragmentChatRoomListBinding binding;
    private ChatRoomListViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_room_list, container, false);
        viewModel = new ViewModelProvider(this).get(ChatRoomListViewModel.class);

        return binding.getRoot();
    }
}