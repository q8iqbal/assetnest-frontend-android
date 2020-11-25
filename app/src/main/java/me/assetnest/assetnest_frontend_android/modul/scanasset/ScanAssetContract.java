package me.assetnest.assetnest_frontend_android.modul.scanasset;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public interface ScanAssetContract {
    interface Presenter extends BasePresenter {
        void loadAssetByCode(String assetCode);
        void loadAssetById(int assetId);
        void saveStatusChange(int assetId, String newStatus);
    }

    interface View extends BaseView<Presenter>{
        void setCurrentAssetId(int assetId);
        void setCurrentAssetCode(String code);
        void startLoading();
        void endLoading();
        void showAsset(Asset asset);
        void showToastMessage(String message);
        void resetFields();
        void redirectToLogin();
    }

    interface Interactor {
        String getToken();
        void requestGetAssetByCode(String assetCode, RequestCallback<Asset> requestCallback);
        void requestGetAssetById(int assetId, RequestCallback<Asset> requestCallback);
        void putAsset(int assedId, String newStatus, RequestCallback<Asset> requestCallback);
    }
}
