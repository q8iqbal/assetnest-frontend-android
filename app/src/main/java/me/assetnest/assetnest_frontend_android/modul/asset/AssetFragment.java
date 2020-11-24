package me.assetnest.assetnest_frontend_android.modul.asset;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;


public class AssetFragment extends BaseFragment<AssetActivity, AssetContract.Presenter> implements AssetContract.View {
    Asset asset;
    String TAG = "TES_API";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYXNzZXRuZXN0Lm1lXC9sb2dpblwvbW9iaWxlIiwiaWF0IjoxNjA2MDc2MTkxLCJuYmYiOjE2MDYwNzYxOTEsImp0aSI6ImU1aTFUdjRudVFQREhCU1EiLCJzdWIiOjYsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.4FFzXe-0xn8gSoYNNJrCtmE_Z1n1CGwRblkOtsypQQY";
    TextView tvAssetName;
    public AssetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        AndroidNetworking.initialize(getActivity().getApplicationContext());
        fragmentView = inflater.inflate(R.layout.fragment_detail_asset, container, false);
        mPresenter = new AssetPresenter(this, new AssetInteractor(UtilProvider.getSharedPreferenceUtil()) );
        mPresenter.start();
        setTitle("Aset");
        Log.d(TAG, "app jalan");
        initView();
        mPresenter.requestAsset();
        return fragmentView;
    }

    public void initView(){
        tvAssetName = fragmentView.findViewById(R.id.tv_name_value);
    }
    @Override
    public void setPresenter(AssetContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showAsset(Asset asset) {
        Log.d(TAG, asset.getName());
        tvAssetName.setText(asset.getName());
    }

    @Override
    public void showError(String errorMessage) {

    }
}
