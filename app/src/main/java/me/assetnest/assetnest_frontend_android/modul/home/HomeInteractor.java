package me.assetnest.assetnest_frontend_android.modul.home;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import me.assetnest.assetnest_frontend_android.api_response.PaginatedAssetResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class HomeInteractor implements HomeContract.Interactor {
    private SharedPreferenceUtil sharedPreferenceUtil;
    String TAG = "TES_API";
    String token;

    HomeInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
        token = this.sharedPreferenceUtil.getToken();
        Log.d(TAG, token);
    }

    @Override
    public void requestListAsset(final RequestCallback<List<Asset>> requestCallback) {
        AndroidNetworking.get(Constant.SHOW_ASSET)
                .addHeaders("Authorization", token)
                .build()
                .getAsObject(PaginatedAssetResponse.class, new ParsedRequestListener<PaginatedAssetResponse>() {
                    @Override
                    public void onResponse(PaginatedAssetResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response.data.getData());
                        }
                        else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }
}
