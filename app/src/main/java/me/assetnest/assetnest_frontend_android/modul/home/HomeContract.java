package me.assetnest.assetnest_frontend_android.modul.home;

import java.util.List;

import me.assetnest.assetnest_frontend_android.api_response.PaginatedResponse;
import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
//        void redirectToAddTask();
//        void redirectToFormTask(int type, int pos, int id);
    }

    interface Presenter extends BasePresenter {
        List<Asset> getListAsset(String filter);
    }
    interface Interactor{
        List<Asset> requestListAssset(String filter);
    }
}
