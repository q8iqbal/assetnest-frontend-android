package me.assetnest.assetnest_frontend_android.modul.history;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.assetnest.assetnest_frontend_android.api_response.PaginatedAssetResponse;
import me.assetnest.assetnest_frontend_android.api_response.PaginatedHistoriesResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.model.History;
import me.assetnest.assetnest_frontend_android.model.PaginatedHistories;
import me.assetnest.assetnest_frontend_android.model.User;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class HistoryInteractor implements HistoryContract.Interactor {
    private SharedPreferenceUtil sharedPreferenceUtil;
    String TAG = "TES_API";
    String token;
    String SHOW_MY_HISTORY;

    HistoryInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
        token = this.sharedPreferenceUtil.getToken();
        SHOW_MY_HISTORY = Constant.BASE_URL+"users/"+getUserId()+"/history";
        Log.d(TAG, token);
    }
    public int getUserId() {
        String userJSON = this.sharedPreferenceUtil.getUser();
        Gson g = new Gson();
        User user = g.fromJson(userJSON, User.class);
        return user.getId();
    }
    @Override
    public void requestPagedHistories(final RequestCallback<List<History>> requestCallback, final int page, final List<History> prefList) {
        AndroidNetworking.get(SHOW_MY_HISTORY+"?page="+page)
                .addHeaders("Authorization", token)
                .build()
                .getAsObject(PaginatedHistoriesResponse.class, new ParsedRequestListener<PaginatedHistoriesResponse>() {
                    @Override
                    public void onResponse(PaginatedHistoriesResponse response) {
                        if(response != null){
                            List<History> histories = new ArrayList<>(prefList);
                            histories.addAll(response.data.getData());
                            requestCallback.requestSuccess(histories);
                            Log.d(TAG, page+" = "+response.data.getLast_page());
                            if(page!=response.data.getLast_page()){
                                Log.d(TAG, "iterasi ke = "+page);
                                requestPagedHistories(requestCallback, page+1, histories);
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
