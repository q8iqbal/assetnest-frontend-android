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
    List<Asset> listAsset;
    String TAG = "TES_API";
    ListView lvAsset;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYXNzZXRuZXN0Lm1lXC9sb2dpbiIsImlhdCI6MTYwNTk1NzUzOCwiZXhwIjoxNjA1OTYxMTM4LCJuYmYiOjE2MDU5NTc1MzgsImp0aSI6IjMxZk5PeXRXUHRxZXhSZ3giLCJzdWIiOjYsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.L5KD9im6X75333_zyZkM9LHL3eN3ZEA-j9jHTUUi8aE";
    SearchView svAsset;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        AndroidNetworking.initialize(getActivity().getApplicationContext());
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        mPresenter = new HomePresenter(this, new HomeInteractor(UtilProvider.getSharedPreferenceUtil()));
        mPresenter.start();
        listAsset = mPresenter.getListAsset("");
        Log.d(TAG, "panjang e : "+listAsset.size());
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
//    public void getAllPagesFromAPI(final String s){
//        AndroidNetworking.get("http://api.assetnest.me/assets"+s)
//                .addHeaders("Authorization", "Bearer "+token)
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // do anything with response
//                        try {
//                            for(int i=1; i<=response.getJSONObject("data").getInt("last_page"); i++){
//                                getDataPerPageFromAPI(i,s);
//                            }
//                        } catch (JSONException e) {
//                            Log.d(TAG, "Response masuk, tapi catch");
//                            e.printStackTrace();
//                        }
//                    }
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                        Log.d(TAG, "Response gagal "+error.getErrorCode());
//                    }
//                });
//
//
//    }
//    public void getDataPerPageFromAPI(int i, String s){
//        AndroidNetworking.get("http://api.assetnest.me/assets"+s)
//                .addQueryParameter("page", String.valueOf(i))
//                .addHeaders("Authorization", "Bearer "+token)
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // do anything with response
//                        try {
//                            JSONArray responseData = response.getJSONObject("data").getJSONArray("data");
//                            Log.d(TAG, "Response masuk"+responseData);
//                            if (responseData != null) {
//                                for (int i=0;i<responseData.length();i++){
//                                    Gson g = new Gson();
//                                    Asset a = g.fromJson(responseData.getString(i), Asset.class);
//                                    listAsset.add(a);
//                                }
//                            }
//                            AssetAdapter adapter = new AssetAdapter(getActivity().getApplicationContext(), (ArrayList<Asset>) listAsset);
//                            lvAsset.setAdapter(adapter);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                        Log.d(TAG, "Response gagal "+error.getErrorCode());
//                    }
//                });
//    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
