package me.assetnest.assetnest_frontend_android.modul.profile;

import com.google.gson.Gson;

import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.model.Company;
import me.assetnest.assetnest_frontend_android.model.User;

public class ProfilePresenter implements ProfileContract.Presenter{
    private final ProfileContract.View view;
    private final ProfileContract.Interactor interactor;

    public ProfilePresenter(ProfileContract.View view, ProfileContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {}

    public User parseUser(){
        String userJSON = interactor.getUser();
        Gson g = new Gson();
        User user = g.fromJson(userJSON, User.class);
        return user;
    }

    @Override
    public void getUser() {
        String userJSON = interactor.getUser();
        Gson g = new Gson();
        User user = g.fromJson(userJSON, User.class);
        view.showProfile(user);
    }

    @Override
    public void requestCompany() {
        //view.startLoading();
        interactor.requestCompany(new RequestCallback<Company>() {
            @Override
            public void requestSuccess(Company data) {
                //view.endLoading();
                view.showCompany(data);
            }
            @Override
            public void requestFailed(String errorMessage) {
                //view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}