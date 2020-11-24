package me.assetnest.assetnest_frontend_android.modul.history;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import me.assetnest.assetnest_frontend_android.api_response.PaginatedAssetResponse;
import me.assetnest.assetnest_frontend_android.api_response.PaginatedHistoriesResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.model.History;
import me.assetnest.assetnest_frontend_android.model.PaginatedHistories;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class HistoryInteractor implements HistoryContract.Interactor {
    private SharedPreferenceUtil sharedPreferenceUtil;
    String TAG = "TES_API";
    String token;

    HistoryInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
        token = this.sharedPreferenceUtil.getToken();
        Log.d(TAG, token);
    }

    @Override
    public void requestListHistories(final RequestCallback<List<History>> requestCallback) {
        AndroidNetworking.get(Constant.SHOW_ASSET)
                .addHeaders("Authorization", token)
                .build()
                .getAsObject(PaginatedHistoriesResponse.class, new ParsedRequestListener<PaginatedHistoriesResponse>() {
                    @Override
                    public void onResponse(PaginatedHistoriesResponse response) {
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
