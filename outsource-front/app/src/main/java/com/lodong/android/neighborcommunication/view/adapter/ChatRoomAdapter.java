package com.lodong.android.neighborcommunication.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.chatroomlist.ChatRoomListFragment;

import java.util.List;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {
    private List<ChatRoomDTO> chatRoomList;
    private ChatRoomListFragment.OnChatRoomClickListener onChatRoomClickListener;

    public ChatRoomAdapter() {}

    public void changeMemberListAdapter(List<ChatRoomDTO> chatRoomList){
        this.chatRoomList = chatRoomList;
        notifyDataSetChanged();
    }

    public void setOnChatRoomClickListener(ChatRoomListFragment.OnChatRoomClickListener onChatRoomClickListener) {
        this.onChatRoomClickListener = onChatRoomClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNickName, txtLastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNickName = itemView.findViewById(R.id.txt_nickName);
            txtLastMessage = itemView.findViewById(R.id.txt_last_message);
        }

        public void onBind(ChatRoomDTO chatRoomDTO){
            String nickName = null;
            if(UserInfo.getInstance().getNickName().equals(chatRoomDTO.getRoomUserOne())){
                nickName = chatRoomDTO.getRoomUserTwo();
            }else{
                nickName = chatRoomDTO.getRoomUserOne();
            }

            txtNickName.setText(nickName);

            itemView.setOnClickListener(view -> {
                intentChatRoom(chatRoomDTO);
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chatroom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(this.chatRoomList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatRoomList == null ? 0 : chatRoomList.size();
    }

    public void intentChatRoom(ChatRoomDTO chatRoomDTO){
        onChatRoomClickListener.onClick(chatRoomDTO);
    }

}
