package me.assetnest.assetnest_frontend_android.modul.home;

/**
 * Created by fahrul on 13/03/19.
 */

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performFormTask(int type, int pos, int id) {
        view.redirectToFormTask(type, pos, id);
    }
}
