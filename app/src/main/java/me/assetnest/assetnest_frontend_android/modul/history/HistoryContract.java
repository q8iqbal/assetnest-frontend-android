package me.assetnest.assetnest_frontend_android.modul.history;

import java.util.List;

import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.model.History;

public interface HistoryContract {
    interface View extends BaseView<Presenter> {
        void startLoading();
        void endLoading();
        void showListHistories(List<History> histories);
        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void requestListAsset();
    }
    interface Interactor{
//        void requestListHistories(RequestCallback<List<History>> requestCallback);
        void requestPagedHistories(RequestCallback<List<History>> requestCallback, int page, List<History> prefList);
    }
}
