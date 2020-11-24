package me.assetnest.assetnest_frontend_android.modul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.utils.AssetAdapter;

public class API extends AppCompatActivity {

    List<Asset> lists = new ArrayList<>();
    String TAG = "TES_API";
    ListView lvFake;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYXNzZXRuZXN0Lm1lXC9sb2dpbiIsImlhdCI6MTYwNTk1NzUzOCwiZXhwIjoxNjA1OTYxMTM4LCJuYmYiOjE2MDU5NTc1MzgsImp0aSI6IjMxZk5PeXRXUHRxZXhSZ3giLCJzdWIiOjYsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.L5KD9im6X75333_zyZkM9LHL3eN3ZEA-j9jHTUUi8aE";
    SearchView svAsset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        svAsset = findViewById(R.id.svAsset);
        lvFake = findViewById(R.id.lvAsset);
        AndroidNetworking.initialize(getApplicationContext());
        getAllPagesFromAPI("");
        svAsset.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(API.this, "Submitted text : "+s, Toast.LENGTH_SHORT).show();
                lists.clear();
                if(!s.isEmpty())
                    getAllPagesFromAPI("?filter[name]="+s);
                else
                    getAllPagesFromAPI("");
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
                getAllPagesFromAPI("");
                return false;
            }
        });
    }
    public void getAllPagesFromAPI(final String s){
        AndroidNetworking.get("http://api.assetnest.me/assets"+s)
                .addHeaders("Authorization", "Bearer "+token)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            for(int i=1; i<=response.getJSONObject("data").getInt("last_page"); i++){
                                getDataPerPageFromAPI(i,s);
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
    public void getDataPerPageFromAPI(int i, String s){
        AndroidNetworking.get("http://api.assetnest.me/assets"+s)
                .addQueryParameter("page", String.valueOf(i))
                .addHeaders("Authorization", "Bearer "+token)
                .setPriority(Priority.LOW)
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
                                    lists.add(a);
                                }
                            }
                            AssetAdapter adapter = new AssetAdapter(getApplicationContext(), (ArrayList<Asset>) lists);
                            lvFake.setAdapter(adapter);
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
}