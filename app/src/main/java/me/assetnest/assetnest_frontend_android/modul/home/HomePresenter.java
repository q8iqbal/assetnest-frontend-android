package me.assetnest.assetnest_frontend_android.modul.home;

import java.util.List;

import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

/**
 * Created by fahrul on 13/03/19.
 */

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;
    private final HomeContract.Interactor interactor;

    public HomePresenter(HomeContract.View view, HomeContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {}

    @Override
    public void requestListAsset() {
        view.startLoading();
        interactor.requestListAsset(new RequestCallback<List<Asset>>() {
            @Override
            public void requestSuccess(List<Asset> data) {
                view.endLoading();
                view.showListAsset(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
