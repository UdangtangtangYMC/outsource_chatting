package com.lodong.android.neighborcommunication.view.signup;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.view.callback.SignUpCallBack;

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
                Dialog dialog = new Dialog(mRef.get());
                dialog.setContentView(R.layout.dialog_sign_up_complete);
                dialog.show();
            }

            @Override
            public void onFailed() {
                Toast.makeText(mRef.get(), "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
            }
        };
    }
}
