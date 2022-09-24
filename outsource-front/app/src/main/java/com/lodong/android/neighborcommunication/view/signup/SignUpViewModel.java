package com.lodong.android.neighborcommunication.view.signup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.data.UserInfo;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.utils.preferences.PreferenceManager;
import com.lodong.android.neighborcommunication.view.callback.SignUpCallBack;
import com.lodong.android.neighborcommunication.view.login.LoginActivity;

import java.lang.ref.WeakReference;

public class SignUpViewModel extends ViewModel {
    private final String TAG = SignUpViewModel.class.getSimpleName();
    private WeakReference<Activity> mRef;
    private Repository repository;

    public void setParent(Activity activity){
        this.mRef = new WeakReference<>(activity);
        init();
    }

    public void init(){
        repository = RepositoryImpl.getInstance();
        repository.setSignUpCompleteCallBack(getSignUpCallback());
    }

    public void signUp(String id, String password, String nickName){
        repository.signUp(id, password, nickName);
    }

    private SignUpCallBack getSignUpCallback() {
        return new SignUpCallBack() {
            @Override
            public void onSuccess() {
                View dialogView = mRef.get().getLayoutInflater().inflate(R.layout.dialog_sign_up_complete, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(mRef.get());
                builder.setView(dialogView);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button okButton = dialogView.findViewById(R.id.btn_ok);

                okButton.setOnClickListener(view -> {
                    mRef.get().startActivity(new Intent(mRef.get(), LoginActivity.class));
                    alertDialog.dismiss();
                });

            }

            @Override
            public void onFailed() {
                Toast.makeText(mRef.get(), "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
            }
        };
    }
}
