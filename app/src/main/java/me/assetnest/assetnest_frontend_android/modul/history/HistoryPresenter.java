package me.assetnest.assetnest_frontend_android.modul.history;

/**
 * Created by fahrul on 13/03/19.
 */

public class HistoryPresenter implements HistoryContract.Presenter{
    private final HistoryContract.View view;

    public HistoryPresenter(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performFormTask(int type, int pos, int id) {
        view.redirectToFormTask(type, pos, id);
    }
}
