package me.assetnest.assetnest_frontend_android.modul.home;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.assetnest.assetnest_frontend_android.api_response.PaginatedResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.utils.AssetAdapter;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class HomeInteractor implements HomeContract.Interactor {
    private SharedPreferenceUtil sharedPreferenceUtil;
    String TAG = "TES_API";
    String token;
    List<Asset> listAsset = new ArrayList<>();

    HomeInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
        token = this.sharedPreferenceUtil.getToken();
        Log.d(TAG, token);
    }

    public void requestAllPages(final String filter) {
        Log.d(TAG, "method req e i dah jalan");
        AndroidNetworking.get(Constant.SHOW_ASSET+filter)
                .addHeaders("Authorization", token)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, "Response masuk lebih cepat");

                        try {
                            for(int i=1; i<=response.getJSONObject("data").getInt("last_page"); i++){
                                requestPerPages(i,filter);
                            }
                        } catch (JSONException e) {
                            Log.d(TAG, "Response masuk, tapi catch");
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d(TAG, "Response gagal "+error.getErrorCode());
                    }
                });
    }

    public void requestPerPages(int page, String filter) {
        AndroidNetworking.get(Constant.SHOW_ASSET+filter)
                .addQueryParameter("page", String.valueOf(page))
                .addHeaders("Authorization", token)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            JSONArray responseData = response.getJSONObject("data").getJSONArray("data");
                            Log.d(TAG, "Response masuk"+responseData);
                            if (responseData != null) {
                                for (int i=0;i<responseData.length();i++){
                                    Gson g = new Gson();
                                    Asset a = g.fromJson(responseData.getString(i), Asset.class);
                                    listAsset.add(a);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d(TAG, "Response gagal "+error.getErrorCode());
                    }
                });
    }

    @Override
    public List<Asset> requestListAssset(String filter) {
        requestAllPages(filter);
        Log.d(TAG, "udah keburu return");
        return listAsset;
    }
}
