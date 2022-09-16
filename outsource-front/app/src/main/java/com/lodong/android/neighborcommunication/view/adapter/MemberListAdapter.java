package com.lodong.android.neighborcommunication.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;

import org.w3c.dom.Text;

import java.util.List;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.ViewHolder> {
    private List<MemberDTO> memberList;

    public MemberListAdapter() {}

    public void changeMemberListAdapter(List<MemberDTO> memberList){
        this.memberList = memberList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtStatusMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtStatusMessage = itemView.findViewById(R.id.txt_status_message);
        }

        public void onBind(MemberDTO member){
            String name = member.getName();
            String statusMessage = member.getStatusMessage();

            txtName.setText(name);
            txtStatusMessage.setText(statusMessage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(this.memberList.get(position));
    }

    @Override
    public int getItemCount() {
        return memberList == null ? 0 : memberList.size();
    }

}
