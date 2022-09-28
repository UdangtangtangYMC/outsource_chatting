package com.lodong.android.neighborcommunication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.Api;
import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.repository.model.ChatMessageDTO;
import com.lodong.android.neighborcommunication.utils.preferences.Code;

import java.util.List;

public class ChattingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessageDTO> chatMessageList;
    private String nickName;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setChatMessageList(List<ChatMessageDTO> chatMessageList) {
        this.chatMessageList = chatMessageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(viewType == Code.ViewType.CENTER_CONTENT){
            view = inflater.inflate(R.layout.item_content, parent, false);
            return new CenterViewHolder(view);
        }else if(viewType == Code.ViewType.LEFT_CONTENT){
            view = inflater.inflate(R.layout.item_left_content, parent, false);
            return new LeftViewHolder(view);
        }else{
            view = inflater.inflate(R.layout.item_right_content, parent, false);
            return new RightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CenterViewHolder){
            ((CenterViewHolder)holder).content.setText(chatMessageList.get(position).getMessage());
        }else if(holder instanceof LeftViewHolder){
            ((LeftViewHolder) holder).txtName.setText(nickName);
            ((LeftViewHolder) holder).txtContent.setText(chatMessageList.get(position).getMessage());
        }else{
            ((RightViewHolder) holder).content.setText(chatMessageList.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatMessageList == null ? 0 : chatMessageList.size();
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtContent;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            txtContent = itemView.findViewById(R.id.txt_content);
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView content;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            this.content = itemView.findViewById(R.id.txt_content);
        }
    }

    public class CenterViewHolder extends RecyclerView.ViewHolder{
        TextView content;

        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.content = itemView.findViewById(R.id.txt_content);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessageList.get(position).getViewType();
    }
}
