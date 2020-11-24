package me.assetnest.assetnest_frontend_android.modul.editProfile;

import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class EditProfileInteractor implements EditProfileContract.Interactor{
    private SharedPreferenceUtil sharedPreferenceUtil;

    EditProfileInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
    }

    @Override
    public String getUser() {
        return sharedPreferenceUtil.getUser();
    }
}
