package me.assetnest.assetnest_frontend_android.modul.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import me.assetnest.assetnest_frontend_android.databinding.ActivityLoginBinding;
import me.assetnest.assetnest_frontend_android.modul.asset.AssetActivity;
import me.assetnest.assetnest_frontend_android.modul.home.HomeActivity;
import me.assetnest.assetnest_frontend_android.modul.profile.ProfileActivity;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    private ActivityLoginBinding binding;
    private LoginPresenter presenter;

    private void initView(){
        binding.btLogin.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setPresenter(new LoginPresenter(this, new LoginInteractor(UtilProvider.getSharedPreferenceUtil())));
        initView();

        presenter.start();
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter =  presenter;
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess() {
       this.finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btLogin.getId()){
            onButtonLoginClick();
        }
    }

    private void onButtonLoginClick(){
        String email = Objects.requireNonNull(binding.etEmail.getText()).toString();
        String password = Objects.requireNonNull(binding.etPassword.getText()).toString();

        if(email.isEmpty()){
            binding.etEmail.setError("this cannot be empty");
        }
        else if(password.isEmpty()){
            binding.etPassword.setError("this cannot be empty");
        }
        else {
            presenter.login(email, password);
        }
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
