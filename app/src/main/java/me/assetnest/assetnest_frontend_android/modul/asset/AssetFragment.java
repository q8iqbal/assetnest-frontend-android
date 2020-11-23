package me.assetnest.assetnest_frontend_android.modul.asset;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.model.History;
import me.assetnest.assetnest_frontend_android.util.HistoryAdapter;


public class AssetFragment extends BaseFragment<AssetActivity, AssetContract.Presenter> implements AssetContract.View {
    Asset asset;
    String TAG = "TES_API";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYXNzZXRuZXN0Lm1lXC9sb2dpblwvbW9iaWxlIiwiaWF0IjoxNjA2MDc2MTkxLCJuYmYiOjE2MDYwNzYxOTEsImp0aSI6ImU1aTFUdjRudVFQREhCU1EiLCJzdWIiOjYsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.4FFzXe-0xn8gSoYNNJrCtmE_Z1n1CGwRblkOtsypQQY";

    public AssetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_detail_asset, container, false);
        mPresenter = new AssetPresenter(this);
        mPresenter.start();
        setTitle("Aset");
        Log.d(TAG, "app jalan");

        AndroidNetworking.initialize(getActivity().getApplicationContext());
        getDataPerPageFromAPI();

        return fragmentView;
    }
    public void getDataPerPageFromAPI(){
        Log.d(TAG, "fungsi kepanggil");

        AndroidNetworking.get("http://api.assetnest.me/assets/20")
                .addHeaders("Authorization", "Bearer "+token)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            JSONObject responseData = response.getJSONObject("data");
                            Log.d(TAG, "Response masuk"+responseData);
                            Gson g = new Gson();
                            asset = g.fromJson(String.valueOf(responseData), Asset.class);
                            Log.d(TAG, "Asset masuk : "+asset.getName());

//                            if (responseData != null) {
//                                for (int i=0;i<responseData.length();i++){
//                                    Gson g = new Gson();
//                                    History h = g.fromJson(responseData.getString(i), History.class);
//                                    histories.add(h);
//                                }
//                            }
//                            HistoryAdapter adapter = new HistoryAdapter(getActivity().getApplicationContext(), (ArrayList<History>) histories);
//                            lvHistories.setAdapter(adapter);
                        } catch (JSONException e) {
                            Log.d(TAG, "Response caytch");
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
    public void setPresenter(AssetContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToFormTask(int type, int pos, int id) {
//        Intent intent = new Intent(activity, FormTaskActivity.class);
//        intent.putExtra("id", id);
//        intent.putExtra("formType", type);
//        intent.putExtra("position", pos);
//        startActivity(intent);
//        activity.finish();
    }
}
