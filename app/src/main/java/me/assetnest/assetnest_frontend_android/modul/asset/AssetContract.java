package me.assetnest.assetnest_frontend_android.modul.asset;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;

public interface AssetContract {
    interface View extends BaseView<Presenter> {
//        void redirectToAddTask();
        void redirectToFormTask(int type, int pos, int id);
    }

    interface Presenter extends BasePresenter {
//        void performAddTask();
        void performFormTask(int type, int pos, int id);
    }
}
