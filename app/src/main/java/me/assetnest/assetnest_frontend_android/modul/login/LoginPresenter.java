package me.assetnest.assetnest_frontend_android.modul.login;

import android.util.Log;

import com.google.gson.Gson;

import me.assetnest.assetnest_frontend_android.api_response.LoginResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;
    private LoginContract.Interactor interactor;

    public LoginPresenter(LoginContract.View view, LoginContract.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void login(String email, String password){
        view.startLoading();
        interactor.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                interactor.saveToken(data.token_type+" "+data.token);
                interactor.saveUser(new Gson().toJson(data.user));
                view.endLoading();
                view.loginSuccess();
            }
            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.loginFailed(errorMessage);
            }
        });
    }

    @Override
    public void start() {
        if (interactor.getToken() != null){
            view.loginSuccess();
        }
    }
}
