package com.lodong.android.neighborcommunication.view.memberlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;

import java.lang.ref.WeakReference;
import java.util.List;

public class MemberListViewModel extends ViewModel {
    private WeakReference<Activity> mRef;
    private MutableLiveData<List<MemberDTO>> memberListML = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorML = new MutableLiveData<>();
    private Repository repository;

    public String userNickName;
    public ObservableField<String> userStatusMessage = new ObservableField<>();

    public void setParent(Activity activity){
        this.mRef = new WeakReference<>(activity);
    }

    public void init(){
        repository = RepositoryImpl.getInstance();
        repository.setGetMemberListCallBack(getMemberListCallBack());

        userNickName = UserInfo.getInstance().getNickName();
        userStatusMessage.set(UserInfo.getInstance().getMessage());
    }

    public void getMemberList() {
        repository.getMemberList(UserInfo.getInstance().getId());
    }

    public void showAlterStatusMessageDialog(){
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
            if(!changeMessage.equals(nowMessage)){
                //서버 전송 및 sharedPreference, 현재 객체 값 수정
                repository.changeStatusMessage(UserInfo.getInstance().getId(), changeMessage);
                PreferenceManager.setStatusMessage(mRef.get(), changeMessage);
                UserInfo.getInstance().setMessage(changeMessage);
                userStatusMessage.set(changeMessage);
            }else{
                Toast.makeText(mRef.get(), "변경된 상태메시지가 없습니다.", Toast.LENGTH_SHORT).show();
            }
            alertDialog.dismiss();
        });

        cancelButton.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
    }

    public MutableLiveData<List<MemberDTO>> getMemberListML(){
        return this.memberListML;
    }

    public MutableLiveData<Throwable> getErrorML(){
        return this.errorML;
    }

    public GetMemberListCallBack getMemberListCallBack(){
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
}
