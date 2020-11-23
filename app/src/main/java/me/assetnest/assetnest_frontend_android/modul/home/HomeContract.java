package me.assetnest.assetnest_frontend_android.modul.home;

import java.util.List;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void startLoading();
        void endLoading();
        void showListAsset(List<Asset> assets);
        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void requestListAsset();
    }
    interface Interactor{
        void requestListAsset(RequestCallback<List<Asset>> requestCallback);
    }
}
