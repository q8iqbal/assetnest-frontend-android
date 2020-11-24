package me.assetnest.assetnest_frontend_android.modul.profile;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.model.Company;
import me.assetnest.assetnest_frontend_android.model.User;

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void showProfile(User user);
        void showCompany(Company company);
        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void getUser();
        void requestCompany();
    }

    interface Interactor{
        String getUser();
        void requestCompany(RequestCallback<Company> requestCallback);
    }
}
