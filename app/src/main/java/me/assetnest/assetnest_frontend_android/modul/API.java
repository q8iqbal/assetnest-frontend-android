package me.assetnest.assetnest_frontend_android.modul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

import me.assetnest.assetnest_frontend_android.R;

public class API extends AppCompatActivity {

    Button btn;
    List<Post> lists = new ArrayList<>();
    String TAG = "TES_API";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        btn = findViewById(R.id.buttonTest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testApi();
                for(Post list : lists){
                    Log.d(TAG, "UserId : "+list.getUserId());
                    Log.d(TAG, "Id : "+list.getId());
                    Log.d(TAG, "Title : "+list.getBody());
                    Log.d(TAG, "Body : "+list.getBody());
                }
//                Toast.makeText(getApplicationContext(), "bisane", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void testApi(){
        AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Post.class, new ParsedRequestListener<List<Post>>() {
                    @Override
                    public void onResponse(List<Post> posts) {
                        // do anything with response
                        lists = posts;
                        Log.d(TAG, "Response masuk");
                    }
                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d("TES API", "Response gagal");

                    }
                });
    }
}