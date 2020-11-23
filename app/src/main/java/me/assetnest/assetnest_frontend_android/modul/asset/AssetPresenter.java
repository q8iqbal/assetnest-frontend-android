package me.assetnest.assetnest_frontend_android.modul.asset;

import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public class AssetPresenter implements AssetContract.Presenter{
    private final AssetContract.View view;
    private final AssetContract.Interactor interactor;
    public AssetPresenter(AssetContract.View view, AssetContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {}

    @Override
    public void requestAsset() {
        view.startLoading();
        interactor.requestAsset(new RequestCallback<Asset>() {
            @Override
            public void requestSuccess(Asset data) {
                view.endLoading();
                view.showAsset(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
