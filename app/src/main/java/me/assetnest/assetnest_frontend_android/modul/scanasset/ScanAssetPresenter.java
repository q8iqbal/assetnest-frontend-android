package me.assetnest.assetnest_frontend_android.modul.scanasset;

import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public class ScanAssetPresenter implements ScanAssetContract.Presenter {
    private final ScanAssetContract.View view;
    private final ScanAssetContract.Interactor interactor;

    public ScanAssetPresenter(ScanAssetContract.View view,
                              ScanAssetContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        if (interactor.getToken() == null) {
            view.redirectToLogin();
        }
        view.setCurrentAssetId(-1);
    }

    private RequestCallback<Asset> createGetRequestCallBack() {
        return new RequestCallback<Asset>() {
            @Override
            public void requestSuccess(Asset data) {
                view.endLoading();
                view.setCurrentAssetId(data.getId());
                view.setCurrentAssetCode(data.getCode());
                view.showAsset(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showToastMessage(errorMessage);
                view.resetFields();
            }
        };
    }

    @Override
    public void loadAssetByCode(final String assetCode) {
        view.startLoading();
        interactor.requestGetAssetByCode(assetCode, createGetRequestCallBack());
    }

    @Override
    public void loadAssetById(final int assetId) {
        view.startLoading();
        interactor.requestGetAssetById(assetId, createGetRequestCallBack());
    }

    @Override
    public void saveStatusChange(int assetId, final String newStatus) {
        view.startLoading();
        interactor.putAsset(assetId, newStatus, new RequestCallback<Asset>() {
            @Override
            public void requestSuccess(Asset data) {
                String message = "Asset \"" + data.getCode() + "\" status changed to " + newStatus;

                view.endLoading();
                view.showToastMessage(message);
                view.showAsset(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showToastMessage(errorMessage);
            }
        });
    }
}
