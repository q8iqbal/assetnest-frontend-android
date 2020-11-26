package me.assetnest.assetnest_frontend_android.modul.profile;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.model.Company;
import me.assetnest.assetnest_frontend_android.model.User;

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void showProfile(User user);
        void showCompany(Company company);
    }

    interface Presenter extends BasePresenter {
        void getUser();
        void getCompany();
    }

    interface Interactor{
        String getUser();
        String getCompany();
    }
}
