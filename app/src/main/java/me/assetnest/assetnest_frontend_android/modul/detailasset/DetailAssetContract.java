package me.assetnest.assetnest_frontend_android.modul.detailasset;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public interface DetailAssetContract {
    interface Presenter extends BasePresenter {
        void loadAsset(int id);
        //void loadAssetHistory(String id);
    }

    interface View extends BaseView<DetailAssetPresenter> {
        void startLoading();
        void endLoading();
        void showAsset(Asset asset);
        void showMessage(String message);
        void redirectToLogin();
        //void showHistories();
    }

    interface Interactor {
        void requestAsset(int id, RequestCallback<Asset> responseCallback);
        String getToken();
    }
}
