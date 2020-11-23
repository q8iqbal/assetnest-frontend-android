package me.assetnest.assetnest_frontend_android.modul.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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
import me.assetnest.assetnest_frontend_android.utils.AssetAdapter;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;


public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {
    String TAG = "TES_API";
    ListView lvAsset;
    SearchView svAsset;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        AndroidNetworking.initialize(getActivity().getApplicationContext());
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        lvAsset = fragmentView.findViewById(R.id.lvAsset);
        mPresenter = new HomePresenter(this, new HomeInteractor(UtilProvider.getSharedPreferenceUtil()));
        mPresenter.start();
        mPresenter.requestListAsset();
        setTitle("Home");

//        svAsset = fragmentView.findViewById(R.id.svAsset);
//        lvAsset = fragmentView.findViewById(R.id.lvAsset);
//
//        AssetAdapter adapter = new AssetAdapter(getActivity().getApplicationContext(), (ArrayList<Asset>) listAsset);
//        lvAsset.setAdapter(adapter);
//        getAllPagesFromAPI("");
//        svAsset.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                Toast.makeText(getActivity().getApplicationContext(), "Submitted text : "+s, Toast.LENGTH_SHORT).show();
//                listAsset.clear();
//                if(!s.isEmpty())
//                    getAllPagesFromAPI("?filter[name]="+s);
//                else
//                    getAllPagesFromAPI("");
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });
//        svAsset.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                getAllPagesFromAPI("");
//                return false;
//            }
//        });
        return fragmentView;
    }
    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showListAsset(List<Asset> assets) {
        Log.d(TAG, "panjang asset : "+assets.size());
        Log.d(TAG, "panjang asset : "+assets.get(1).getName());

        AssetAdapter adapter = new AssetAdapter(getActivity().getApplicationContext(), (ArrayList<Asset>) assets);
        lvAsset.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMessage) {

    }
}
