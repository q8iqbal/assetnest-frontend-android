package me.assetnest.assetnest_frontend_android.modul.editProfile;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;

public interface EditProfileContract {
    interface View extends BaseView<Presenter> {
        void redirectToSuccesEdit();
    }

    interface Presenter extends BasePresenter {
        void performEditProfile();
    }

    interface Interactor{
        String getUser();
    }
}
