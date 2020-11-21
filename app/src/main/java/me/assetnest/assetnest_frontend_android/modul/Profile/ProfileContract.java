package me.assetnest.assetnest_frontend_android.modul.Profile;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void redirectToSuccesEdit();
    }

    interface Presenter extends BasePresenter {
        void performEditTask();
        void requestData(String token, String id);
    }
}
