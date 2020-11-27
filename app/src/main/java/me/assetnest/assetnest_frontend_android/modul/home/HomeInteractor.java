package me.assetnest.assetnest_frontend_android.modul.home;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

import me.assetnest.assetnest_frontend_android.api_response.PaginatedAssetResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.model.PaginatedAssets;
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
    public void requestPagedListAsset(final RequestCallback<List<Asset>> requestCallback, final int page, final List<Asset> prefList, final String filter) {
        AndroidNetworking.get(Constant.SHOW_ASSET+"?page="+page+filter)
                .addHeaders("Authorization", token)
                .build()
                .getAsObject(PaginatedAssetResponse.class, new ParsedRequestListener<PaginatedAssetResponse>() {
                    @Override
                    public void onResponse(PaginatedAssetResponse response) {
                        if(response != null){
                            List<Asset> assets = new ArrayList<>(prefList);
                            assets.addAll(response.data.getData());
                            requestCallback.requestSuccess(assets);
                            Log.d(TAG, page+" = "+response.data.getLast_page());
                            if(page!=response.data.getLast_page()){
                                Log.d(TAG, "iterasi ke = "+page);
                                requestPagedListAsset(requestCallback, page+1, assets, filter);
                            }
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
