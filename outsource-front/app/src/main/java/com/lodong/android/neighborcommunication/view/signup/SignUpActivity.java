package com.lodong.android.neighborcommunication.view.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    private final String TAG = SignUpActivity.class.getSimpleName();
    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        binding.setActivity(this);

        viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.
                AndroidViewModelFactory(getApplication())).get(SignUpViewModel.class);
        viewModel.setParent(this);

    }

    public void signUp(){
        String id = binding.edtId.getText().toString();
        String password = binding.edtPassword.getText().toString();
        String nickName = binding.edtNickname.getText().toString();

        viewModel.signUp(id, password, nickName);
    }

}