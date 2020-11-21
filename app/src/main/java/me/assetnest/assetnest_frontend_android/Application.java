package me.assetnest.assetnest_frontend_android;

import com.androidnetworking.AndroidNetworking;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
