package me.assetnest.assetnest_frontend_android.modul.login;

import me.assetnest.assetnest_frontend_android.api_response.LoginResponse;
import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;

public interface LoginContract {
    interface Presenter extends BasePresenter{
        void login(String email, String password);
    }

    interface View extends BaseView<LoginPresenter>{
        void startLoading();
        void endLoading();
        void loginSuccess();
        void loginFailed(String message);
    }

    interface Interactor {
        void requestLogin(String email, String password, RequestCallback<LoginResponse> responseCallback);
        void saveToken(String token);
        String getToken();
        void saveUser(String user);
    }
}
