package me.assetnest.assetnest_frontend_android.modul.scanasset;

import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;

public class ScanAssetPresenter implements ScanAssetContract.Presenter {
    private final ScanAssetContract.View view;
    private final ScanAssetContract.Interactor interactor;
    // String token;

    public ScanAssetPresenter(ScanAssetContract.View view,
                              ScanAssetContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        // token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYXNzZXRuZXN0Lm1lXC9sb2dpbiIsImlhdCI6MTYwNTg3MzgxOCwiZXhwIjoxNjA1ODc3NDE4LCJuYmYiOjE2MDU4NzM4MTgsImp0aSI6IlBGQU1FNEI4bmhCa01kZTUiLCJzdWIiOjQsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.bm8OW8LiMMcU6b2xS4XMVhdkkeruxZ95P7bYryHbQDU";
        if (interactor.getToken() == null) {
            view.redirectToLogin();
        }
        view.setCurrentAssetId(-1);
    }

    @Override
    public void loadAssetData(final String assetCode) {
        view.startLoading();
        interactor.getAsset(assetCode, new RequestCallback<Asset>() {
            @Override
            public void requestSuccess(Asset data) {
                view.endLoading();
                view.setCurrentAssetId(data.getId());
                view.showAsset(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showToastMessage(errorMessage);
            }
        });
    }

    @Override
    public void saveStatusChange(int assetId, final String newStatus) {
        view.startLoading();
        interactor.putAsset(assetId, newStatus, new RequestCallback<Asset>() {
            @Override
            public void requestSuccess(Asset data) {
                String message = "Asset \"" + data.getCode() + "\" status changed to" + newStatus;

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
