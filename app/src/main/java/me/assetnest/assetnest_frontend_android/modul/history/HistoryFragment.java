package me.assetnest.assetnest_frontend_android.modul.history;

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
import me.assetnest.assetnest_frontend_android.modul.MainActivity;
import me.assetnest.assetnest_frontend_android.utils.AssetAdapter;
import me.assetnest.assetnest_frontend_android.utils.HistoryAdapter;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;


public class HistoryFragment extends BaseFragment<MainActivity, HistoryContract.Presenter> implements HistoryContract.View {
    String TAG = "TES_API";
    ListView lvHistories;
    public HistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_history, container, false);
        mPresenter = new HistoryPresenter(this, new HistoryInteractor(UtilProvider.getSharedPreferenceUtil()));
        mPresenter.start();
        lvHistories = fragmentView.findViewById(R.id.lv_histories);

        mPresenter.requestListAsset();
        setTitle("History");
        return fragmentView;
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showListHistories(List<History> histories) {
        HistoryAdapter adapter = new HistoryAdapter(getActivity().getApplicationContext(), (ArrayList<History>) histories);
        lvHistories.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMessage) {

    }
}
