package me.assetnest.assetnest_frontend_android.modul.Profile;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import me.assetnest.assetnest_frontend_android.modul.Profile.ProfileFragment;
import me.assetnest.assetnest_frontend_android.utils.Constant;

public class ProfilePresenter implements ProfileContract.Presenter{
    private final ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performEditTask(){
        view.redirectToSuccesEdit();
    }

    @Override
    public void requestData(String token, String id) {
        AndroidNetworking.get(Constant.BASE_URL + "users/{userId}")
                .addPathParameter("userId", id)
                .addHeaders("Authorization", "Bearer " + token)
                .build()
                .getAsObject();
    }
}
