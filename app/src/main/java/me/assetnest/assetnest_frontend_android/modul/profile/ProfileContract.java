package me.assetnest.assetnest_frontend_android.modul.profile;

import me.assetnest.assetnest_frontend_android.api_response.LoginResponse;
import me.assetnest.assetnest_frontend_android.api_response.LogoutResponse;
import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Company;
import me.assetnest.assetnest_frontend_android.model.User;

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void showProfile(User user);
        void showCompany(Company company);
        void signOut();
    }

    interface Presenter extends BasePresenter {
        void getUser();
        void getCompany();
        void signOut();
    }

    interface Interactor{
        String getUser();
        String getCompany();
        void signOut(RequestCallback<LogoutResponse> responseCallback);
        void clearDataUser();
    }
}
