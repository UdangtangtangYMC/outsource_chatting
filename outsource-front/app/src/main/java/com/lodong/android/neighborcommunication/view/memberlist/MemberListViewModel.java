package com.lodong.android.neighborcommunication.view.memberlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.ChatRoomDTO;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserBlockedCallBack;
import com.lodong.android.neighborcommunication.view.callback.UserUnblockedCallBack;
import com.lodong.android.neighborcommunication.view.chatroom.ChatRoomActivity;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Optional;

public class MemberListViewModel extends ViewModel {
    private final String TAG = MemberListViewModel.class.getSimpleName();
    private WeakReference<Activity> mRef;
    private MutableLiveData<List<MemberDTO>> memberListML = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorML = new MutableLiveData<>();
    private Repository repository;

    public String userNickName;
    public ObservableField<String> userStatusMessage = new ObservableField<>();

    public void setParent(Activity activity) {
        this.mRef = new WeakReference<>(activity);
    }

    public void init() {
        repository = RepositoryImpl.getInstance();
        repository.setGetMemberListCallBack(getMemberListCallBack());

        userNickName = UserInfo.getInstance().getNickName();
        userStatusMessage.set(UserInfo.getInstance().getMessage());
    }

    public void getMemberList() {
        repository.getMemberList(UserInfo.getInstance().getId());
    }

    public void showAlterStatusMessageDialog() {
        View dialogView = mRef.get().getLayoutInflater().inflate(R.layout.dialog_alter_user_info, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mRef.get());
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button okButton = dialogView.findViewById(R.id.btn_ok);
        Button cancelButton = dialogView.findViewById(R.id.btn_cancel);
        EditText edtChangeMessage = dialogView.findViewById(R.id.edt_change_message);

        String nowMessage = UserInfo.getInstance().getMessage();

        edtChangeMessage.setText(nowMessage);

        okButton.setOnClickListener(view -> {
            String changeMessage = edtChangeMessage.getText().toString();
            if (!changeMessage.equals(nowMessage)) {
                //서버 전송 및 sharedPreference, 현재 객체 값 수정
                repository.changeStatusMessage(UserInfo.getInstance().getId(), changeMessage);
                PreferenceManager.setStatusMessage(mRef.get(), changeMessage);
                UserInfo.getInstance().setMessage(changeMessage);
                userStatusMessage.set(changeMessage);
            } else {
                Toast.makeText(mRef.get(), "변경된 상태메시지가 없습니다.", Toast.LENGTH_SHORT).show();
            }
            alertDialog.dismiss();
        });

        cancelButton.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
    }

    public void showOpenMemberSettingDialog(MemberDTO memberDTO) {
        View dialogView = mRef.get().getLayoutInflater().inflate(R.layout.dialog_member_click, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mRef.get());
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ImageButton sendChatting = dialogView.findViewById(R.id.btn_chat);
        ImageButton reject = dialogView.findViewById(R.id.btn_reject);
        TextView txtBlock = dialogView.findViewById(R.id.txt_block);

        if(memberDTO.isBlocked()) {
            reject.setBackgroundResource(R.drawable.ic_baseline_mark_chat_read_24);
            txtBlock.setText(R.string.unblock);
        }
        else {
            reject.setBackgroundResource(R.drawable.ic_baseline_speaker_notes_off_24);
            txtBlock.setText(R.string.block);
        }

        TextView txtMemberName = dialogView.findViewById(R.id.txt_member_name);
        txtMemberName.setText(memberDTO.getNickName());

        sendChatting.setOnClickListener(view -> {
            intentChatRoomActivity(memberDTO.getId(), memberDTO.getNickName());
            Log.d(TAG, memberDTO.toString());
            alertDialog.dismiss();
        });

        reject.setOnClickListener(view -> {
            if(memberDTO.isBlocked()) {
                repository.setUserUnblockedCallBack(getUserUnblockedCallBack());
                unblock(memberDTO);
            } else {
                repository.setUserBlockedCallBack(getUserBlockedCallBack());
                block(memberDTO);
            }
            alertDialog.dismiss();
        });
    }

    public void intentChatRoomActivity(String receiver, String receiverNickName) {
        Optional<ChatRoomDTO> optional = Optional.ofNullable(repository.getChatRoom(receiver));
        Intent intent = new Intent(mRef.get(), ChatRoomActivity.class);
        optional.ifPresent(chatRoomDTO -> {
            Log.d(TAG, "chatRoomDTO : " + chatRoomDTO);
            intent.putExtra("chatRoomId", optional.get().getRoomId());
        });
        intent.putExtra("receiver", receiver);
        intent.putExtra("receiverNickName", receiverNickName);

        Log.d(TAG, "intent ChatRoom");
        mRef.get().startActivity(intent);
    }

    public void block(MemberDTO dto) {
        repository.block(dto);
    }

    public void unblock(MemberDTO dto) {
        repository.unblock(dto);
    }

    public MutableLiveData<List<MemberDTO>> getMemberListML() {
        return this.memberListML;
    }

    public MutableLiveData<Throwable> getErrorML() {
        return this.errorML;
    }

    public GetMemberListCallBack getMemberListCallBack() {
        return new GetMemberListCallBack() {
            @Override
            public void onSuccess(List<MemberDTO> memberList) {
                memberListML.setValue(memberList);
            }

            @Override
            public void onFailed(Throwable t) {
                errorML.setValue(t);
            }
        };
    }

    public UserBlockedCallBack getUserBlockedCallBack() {
        return new UserBlockedCallBack() {
            @Override
            public void onSuccess(MemberDTO memberDTO) {
                memberDTO.setBlocked(true);
            }

            @Override
            public void onFailed(Throwable t) {

            }
        };
    }

    public UserUnblockedCallBack getUserUnblockedCallBack() {
        return new UserUnblockedCallBack() {
            @Override
            public void onSuccess(MemberDTO memberDTO) {
                memberDTO.setBlocked(false);
            }

            @Override
            public void onFailed(Throwable t) {

            }
        };
    }
}
