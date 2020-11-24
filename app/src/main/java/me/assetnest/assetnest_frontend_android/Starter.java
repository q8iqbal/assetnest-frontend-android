package me.assetnest.assetnest_frontend_android;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import me.assetnest.assetnest_frontend_android.utils.UtilProvider;

public class Starter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        UtilProvider.initialize(getApplicationContext());
    }
}
