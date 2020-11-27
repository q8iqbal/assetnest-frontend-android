package me.assetnest.assetnest_frontend_android.modul.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.modul.MainActivity;
import me.assetnest.assetnest_frontend_android.modul.asset.AssetActivity;
import me.assetnest.assetnest_frontend_android.utils.AssetAdapter;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;


public class HomeFragment extends BaseFragment<MainActivity, HomeContract.Presenter> implements HomeContract.View{
    String TAG = "TES_API";
    ListView lvAsset;
    SearchView svAsset;
    ProgressBar pbHome;
    AssetAdapter adapter;
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        lvAsset = fragmentView.findViewById(R.id.lvAsset);
        lvAsset.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Asset item = (Asset) adapter.getItem(i);
                Toast.makeText(getActivity(), "Terpilih list ke "+item.getCode(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AssetActivity.class);
                intent.putExtra("id", item.getId());
                startActivity(intent);
            }
        });
        pbHome = fragmentView.findViewById(R.id.pb_fragment_home);
        mPresenter = new HomePresenter(this, new HomeInteractor(UtilProvider.getSharedPreferenceUtil()));
        mPresenter.start();
        mPresenter.requestListAsset("");
        setTitle("Home");

        svAsset = fragmentView.findViewById(R.id.svAsset);

        svAsset.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getActivity().getApplicationContext(), "Submitted text : "+s, Toast.LENGTH_SHORT).show();
                if(!s.isEmpty())
                    mPresenter.requestListAsset("&filter[name]="+s);
                else
                    mPresenter.requestListAsset("");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        svAsset.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mPresenter.requestListAsset("");
                return false;
            }
        });
        return fragmentView;
    }
    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startLoading() {
        pbHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        pbHome.setVisibility(View.GONE);
    }

    @Override
    public void showListAsset(List<Asset> assets) {
        Log.d(TAG, "panjang asset : "+assets.size());

        adapter = new AssetAdapter(getActivity().getApplicationContext(), (ArrayList<Asset>) assets);
        lvAsset.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMessage) {

    }
}
