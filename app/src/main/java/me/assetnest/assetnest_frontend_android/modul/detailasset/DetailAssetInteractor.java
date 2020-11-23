package me.assetnest.assetnest_frontend_android.modul.detailasset;


import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;

import org.json.JSONArray;
import org.json.JSONObject;

//import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
//import me.assetnest.assetnest_frontend_android.model.Asset;
//import me.assetnest.assetnest_frontend_android.utils.Constant;
//import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class DetailAssetInteractor{
//    private SharedPreferenceUtil sharedPreferenceUtil;
//
//    public DetailAssetInteractor(SharedPreferenceUtil sharedPreferenceUtil) {
//        this.sharedPreferenceUtil = sharedPreferenceUtil;
//    }
//
//    @Override
//    public void requestAsset(int id, RequestCallback<Asset> responseCallback) {
//        AndroidNetworking.get(Constant.BASE_URL+"assets/{id}")
//                .addPathParameter("id", Integer.toString(id))
//                .addHeaders("Authorization",sharedPreferenceUtil.getToken())
//                .setTag("test")
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .get
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONObject assetJson = response.getJSONObject("data");
//                            Asset asset = new Gson().fromJson(assetJson.toString(), Asset.class)
//                            JSONArray assets = response.getJSONObject("data")
//                                    .getJSONArray("data");
//                            JSONObject assetJson = (assets.length() > 0)
//                                    ? assets.getJSONObject(0)
//                                    : null;
//                            Asset asset = (assetJson != null)
//                                    ? new Gson().fromJson(assetJson.toString(), Asset.class)
//                                    : null;
//
//                            Log.d("Assetnest-Android",
//                                    "response: " + response.toString(4));
//                            Log.d("Assetnest-Android",
//                                    "JSONArray assets: " + assets.toString(4));
//                            if (assetJson != null) {
//                                Log.d("Assetnest-Android",
//                                        "JSONObject asset: " + assetJson.toString(4));
//                            }
//                            if (asset == null) {
//                                String errorMessage = "Asset with code \"" + assetCode + "\" not found";
//                                requestCallback.requestFailed(errorMessage);
//                            } else {
//                                requestCallback.requestSuccess(asset);
//                            }
//                        } catch (JSONException e) {
//                            requestCallback.requestFailed("Error: JSONException error.");
//                            e.printStackTrace();
//                            Log.e("Assetnest-Android", "response error: ", e);
//                        }
//                    }
//                    @Override
//                    public void onError(ANError error) {
//                        requestCallback.requestFailed("Error: ANError error.");
//                        Log.e("Assetnest-Android", "ANError error: ", error);
//                    }
//                });
//    }
//
//    @Override
//    public String getToken() { return sharedPreferenceUtil.getToken(); }
}
