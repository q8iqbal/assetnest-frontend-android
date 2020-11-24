package me.assetnest.assetnest_frontend_android.modul.asset;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import me.assetnest.assetnest_frontend_android.api_response.AssetResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class AssetInteractor implements AssetContract.Interactor{
    private SharedPreferenceUtil sharedPreferenceUtil;
    private String token;
    private String TAG = "TES_API";
    public AssetInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
        this.token = sharedPreferenceUtil.getToken();
    }

    @Override
    public void requestAsset(final RequestCallback<Asset> requestCallback) {
        AndroidNetworking.get(Constant.SHOW_ASSET + "/20")
                .addHeaders("Authorization", token)
                .build()
                .getAsObject(AssetResponse.class, new ParsedRequestListener<AssetResponse>() {
                    @Override
                    public void onResponse(AssetResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response.data);
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
