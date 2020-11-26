package me.assetnest.assetnest_frontend_android.modul.profile;

import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class ProfileInteractor implements ProfileContract.Interactor{
    private SharedPreferenceUtil sharedPreferenceUtil;

    ProfileInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
    }

    @Override
    public String getUser() {
        return sharedPreferenceUtil.getUser();
    }

    @Override
    public String getCompany() {
        return sharedPreferenceUtil.getCompany();
    }
}
