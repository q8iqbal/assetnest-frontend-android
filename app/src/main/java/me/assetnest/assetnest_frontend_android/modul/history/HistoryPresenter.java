package me.assetnest.assetnest_frontend_android.modul.history;

import java.util.ArrayList;
import java.util.List;

import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.History;

/**
 * Created by fahrul on 13/03/19.
 */

public class HistoryPresenter implements HistoryContract.Presenter{
    private final HistoryContract.View view;
    private final HistoryContract.Interactor interactor;
    public HistoryPresenter(HistoryContract.View view, HistoryContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {}

    @Override
    public void requestListAsset() {
        view.startLoading();
        interactor.requestPagedHistories(new RequestCallback<List<History>>() {
            @Override
            public void requestSuccess(List<History> data) {
                view.endLoading();
                view.showListHistories(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, 1, new ArrayList<History>());
    }
}
