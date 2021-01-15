package me.assetnest.assetnest_frontend_android.modul.asset;

import java.util.List;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public interface AssetContract {
    interface View extends BaseView<Presenter> {
        void startLoading();
        void endLoading();
        void showAsset(Asset asset);
        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void requestAsset(int id);
    }

    interface Interactor{
        void requestAsset(RequestCallback<Asset> requestCallback, int id);
    }
}
