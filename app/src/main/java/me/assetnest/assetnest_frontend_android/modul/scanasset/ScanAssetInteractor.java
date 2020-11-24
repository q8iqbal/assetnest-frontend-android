package me.assetnest.assetnest_frontend_android.modul.scanasset;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class ScanAssetInteractor implements ScanAssetContract.Interactor {
    SharedPreferenceUtil sharedPreferenceUtil;

    public ScanAssetInteractor(SharedPreferenceUtil sharedPreferenceUtil) {
        this.sharedPreferenceUtil = sharedPreferenceUtil;
    }

    @Override
    public String getToken() {
        return sharedPreferenceUtil.getToken();
    }

    private void requestGetAsset(String url, String pathParameter, String pathParameterValue,
                                 JSONObjectRequestListener jsonObjectrequestListener) {
        AndroidNetworking.get(url)
                .addPathParameter(pathParameter, pathParameterValue)
                .addHeaders("Authorization", sharedPreferenceUtil.getToken())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(jsonObjectrequestListener);
    }

    @Override
    public void requestGetAssetByCode(final String assetCode,
                                      final RequestCallback<Asset> requestCallback) {
        String url = Constant.BASE_URL + "assets/?filter[code]={code}";
        JSONObjectRequestListener jsonObjectrequestListener = new JSONObjectRequestListener() {
            private final String logTag = "Assetnest-Android";

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray assets = response.getJSONObject("data").getJSONArray("data");
                    JSONObject assetJson = (assets.length() > 0)
                            ? assets.getJSONObject(0) : null;
                    Asset asset = (assetJson != null)
                            ? new Gson().fromJson(assetJson.toString(), Asset.class) : null;
                    int indentSpaces = 4;

                    Log.d(logTag, "response: " + response.toString(indentSpaces));
                    Log.d(logTag, "JSONArray assets: " + assets.toString(indentSpaces));
                    if (assetJson != null) {
                        Log.d(logTag, "JSONObject asset: " + assetJson.toString(indentSpaces));
                    }
                    if (asset == null) {
                        String errorMessage = "Asset with code \"" + assetCode + "\" not found";
                        requestCallback.requestFailed(errorMessage);
                    } else {
                        requestCallback.requestSuccess(asset);
                    }
                } catch (JSONException e) {
                    requestCallback.requestFailed(e.getMessage());
                    e.printStackTrace();
                    Log.e(logTag, "JSONException error: ", e);
                }
            }

            @Override
            public void onError(ANError anError) {
                requestCallback.requestFailed(anError.getMessage());
                Log.e(logTag, "ANError error: ", anError);
            }
        };

        requestGetAsset(url, "code", assetCode, jsonObjectrequestListener);
    }

    @Override
    public void requestGetAssetById(final int assetId,
                                    final RequestCallback<Asset> requestCallback) {
        String url = Constant.BASE_URL + "assets/{id}";
        String assetIdString = Integer.toString(assetId);
        JSONObjectRequestListener jsonObjectRequestListener = new JSONObjectRequestListener() {
            private final String logTag = "Assetnest-Android";

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject assetJson = response.getJSONObject("data");
                    Asset asset = new Gson().fromJson(assetJson.toString(), Asset.class);
                    int indentSpaces = 4;

                    Log.d(logTag, "response: " + response.toString(indentSpaces));
                    Log.d(logTag, "JSONObject asset: " + assetJson.toString(indentSpaces));
                    if (asset == null) {
                        String errorMessage = "Asset with id \"" + assetId + "\" not found";
                        requestCallback.requestFailed(errorMessage);
                    } else {
                        requestCallback.requestSuccess(asset);
                    }
                } catch (JSONException e) {
                    requestCallback.requestFailed(e.getMessage());
                    e.printStackTrace();
                    Log.e(logTag, "JSONException error: ", e);
                }
            }

            @Override
            public void onError(ANError anError) {
                requestCallback.requestFailed(anError.getMessage());
                Log.e(logTag, "ANError error: ", anError);
            }
        };

        requestGetAsset(url, "id", assetIdString, jsonObjectRequestListener);
    }

    @Override
    public void putAsset(final int assetId, final String newStatus,
                         final RequestCallback<Asset> requestCallback) {
        AndroidNetworking.put(Constant.BASE_URL + "assets/{id}")
            .addPathParameter("id", Integer.toString(assetId))
            .addHeaders("Authorization", sharedPreferenceUtil.getToken())
            .addJSONObjectBody(createPutAssetJSONObject(newStatus))
            .setTag("test")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(new JSONObjectRequestListener() {
                private final String logTag = "Assetnest-Android";

                @Override
                public void onResponse(JSONObject response) {
                    requestGetAssetById(assetId, requestCallback);
                    try {
//                        requestGetAssetById(assetId, requestCallback);
//                        JSONObject assetJson = response.getJSONObject("data");
//                        Asset asset = new Gson().fromJson(assetJson.toString(), Asset.class);
                        final int indentSpaces = 4;

//                        requestCallback.requestSuccess(asset);
                        Log.d(logTag, "response: " + response.toString(indentSpaces));
//                        Log.d(logTag, "JSONObject asset: " + assetJson.toString(indentSpaces));
                    } catch (JSONException e) {
//                        requestCallback.requestFailed(e.getMessage());
                        e.printStackTrace();
                        Log.e(logTag, "JSONException error: ", e);
                    }
                }
                @Override
                public void onError(ANError anError) {
                    requestCallback.requestFailed(anError.getMessage());
                    Log.e(logTag, "ANError error: ", anError);
                }
            });
    }

    private JSONObject createPutAssetJSONObject(String newStatus) {
        JSONObject putAssetJSONObject = new JSONObject();

        try {
            putAssetJSONObject.put("asset", new JSONObject().put("status", newStatus));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return putAssetJSONObject;
    }
}
