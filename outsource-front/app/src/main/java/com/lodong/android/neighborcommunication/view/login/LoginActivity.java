package com.lodong.android.neighborcommunication.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setActivity(this);

        viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(LoginViewModel.class);
        viewModel.setParent(this);
    }

    public void login(){
        String id = binding.edtId.getText().toString();
        String password = binding.edtPassword.getText().toString();
        viewModel.login(id, password);
    }
}