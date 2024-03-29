package com.lodong.android.neighborcommunication.view.adapter;

import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.ChatRoomDisplayInfo;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.view.chatroomlist.ChatRoomListFragment;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {
    private final String TAG = ChatRoomAdapter.class.getSimpleName();
    private List<ChatRoomDisplayInfo> chatRoomList;
    private ChatRoomListFragment.OnChatRoomClickListener onChatRoomClickListener;

    public ChatRoomAdapter() {}

    public void changeMemberListAdapter(List<ChatRoomDisplayInfo> chatRoomList){
        Collections.sort(chatRoomList, (room1, room2) -> {
            String room1LastMessageTime = room1.getLastMessageTime();
            String room2LastMessageTime = room2.getLastMessageTime();
            LocalDateTime room1LastTime;
            LocalDateTime room2LastTime;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                room1LastTime = LocalDateTime.parse(room1LastMessageTime, formatter);
                room2LastTime = LocalDateTime.parse(room2LastMessageTime, formatter);
                if(room1LastTime.isBefore(room2LastTime)) return 1;
                else if(room1LastTime.isAfter(room2LastTime)) return -1;
                else return 0;
            }
            return 0;
        });
        this.chatRoomList = chatRoomList;
        notifyDataSetChanged();
    }

    public void setOnChatRoomClickListener(ChatRoomListFragment.OnChatRoomClickListener onChatRoomClickListener) {
        this.onChatRoomClickListener = onChatRoomClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNickName, txtLastMessage, txtLastSendTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNickName = itemView.findViewById(R.id.txt_nickName);
            txtLastMessage = itemView.findViewById(R.id.txt_last_message);
            txtLastSendTime = itemView.findViewById(R.id.txt_last_send_time);
        }

            public void onBind(ChatRoomDisplayInfo chatRoom){
                String nickName = chatRoom.getReceiverNickName();
                String lastMessage = chatRoom.getLastMessage();
                String lastTime = chatRoom.getLastMessageTime();

                txtNickName.setText(nickName);
                txtLastMessage.setText(lastMessage);
                txtLastSendTime.setText(lastTime);

            itemView.setOnClickListener(view -> {
                intentChatRoom(chatRoom);
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

    public void intentChatRoom(ChatRoomDisplayInfo chatRoomDisplayInfo){
        onChatRoomClickListener.onClick(chatRoomDisplayInfo);
    }

}
