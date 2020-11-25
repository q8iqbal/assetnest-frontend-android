package me.assetnest.assetnest_frontend_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.assetnest.assetnest_frontend_android.modul.history.HistoryActivity;
import me.assetnest.assetnest_frontend_android.modul.home.HomeActivity;
import me.assetnest.assetnest_frontend_android.modul.profile.ProfileActivity;
import me.assetnest.assetnest_frontend_android.modul.scanasset.ScanAssetActivity;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mn_home) {
            Toast.makeText(getApplicationContext(),"Buka Home", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        } else if (item.getItemId() == R.id.mn_scan) {
            Toast.makeText(getApplicationContext(),"Buka Scan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ScanAssetActivity.class));
        } else if (item.getItemId() == R.id.mn_history) {
            Toast.makeText(getApplicationContext(),"Buka HIstory", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HistoryActivity.class));
        } else if (item.getItemId() == R.id.mn_profile) {
            Toast.makeText(getApplicationContext(),"Buka Profile", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ProfileActivity.class));
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}