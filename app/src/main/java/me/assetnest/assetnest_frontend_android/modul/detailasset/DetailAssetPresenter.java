package me.assetnest.assetnest_frontend_android.modul.detailasset;

import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public class DetailAssetPresenter implements DetailAssetContract.Presenter{
    private DetailAssetContract.View view;
    private DetailAssetContract.Interactor interactor;

    public DetailAssetPresenter(DetailAssetContract.View view, DetailAssetContract.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void loadAsset(int id){
        view.startLoading();
//        interactor.requestAsset(id, new RequestCallback<Asset>() {
//            @Override
//            public void requestSuccess(Asset data) {
//                view.endLoading();
//                view.showAsset(data);
//            }
//
//            @Override
//            public void requestFailed(String errorMessage) {
//                view.endLoading();
//                view.showMessage(errorMessage);
//            }
//        });
    }

    @Override
    public void start() {
        if (interactor.getToken() == null){
            view.redirectToLogin();
        }
    }
}
