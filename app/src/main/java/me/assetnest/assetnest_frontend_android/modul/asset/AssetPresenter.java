package me.assetnest.assetnest_frontend_android.modul.asset;

/**
 * Created by fahrul on 13/03/19.
 */

public class AssetPresenter implements AssetContract.Presenter{
    private final AssetContract.View view;

    public AssetPresenter(AssetContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performFormTask(int type, int pos, int id) {
        view.redirectToFormTask(type, pos, id);
    }
}
