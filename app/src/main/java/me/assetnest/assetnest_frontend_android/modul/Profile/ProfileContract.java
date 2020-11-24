package me.assetnest.assetnest_frontend_android.modul.profile;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.model.User;

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void showProfile(User user);
    }

    interface Presenter extends BasePresenter {
        void getUser();
    }

    interface Interactor{
        String getUser();
    }
}
