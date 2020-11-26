package me.assetnest.assetnest_frontend_android.modul.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.assetnest.assetnest_frontend_android.databinding.ActivitySplashBinding;
import me.assetnest.assetnest_frontend_android.modul.MainActivity;
import me.assetnest.assetnest_frontend_android.modul.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    private int splashTime = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        },splashTime);
    }
}
