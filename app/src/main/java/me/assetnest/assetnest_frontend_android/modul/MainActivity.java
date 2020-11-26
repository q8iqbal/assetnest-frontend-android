package me.assetnest.assetnest_frontend_android.modul;

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

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;
import me.assetnest.assetnest_frontend_android.modul.history.HistoryActivity;
import me.assetnest.assetnest_frontend_android.modul.history.HistoryFragment;
import me.assetnest.assetnest_frontend_android.modul.home.HomeActivity;
import me.assetnest.assetnest_frontend_android.modul.home.HomeFragment;
import me.assetnest.assetnest_frontend_android.modul.profile.ProfileActivity;
import me.assetnest.assetnest_frontend_android.modul.profile.ProfileFragment;
import me.assetnest.assetnest_frontend_android.modul.scanasset.ScanAssetActivity;
import me.assetnest.assetnest_frontend_android.modul.scanasset.ScanAssetFragment;

public class MainActivity extends BaseFragmentHolderActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    HomeFragment homeFragment;
    ScanAssetFragment scanAssetFragment;
    HistoryFragment historyFragment;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initializeFragment() {
        initializeView();
        homeFragment = new HomeFragment();
        scanAssetFragment = new ScanAssetFragment();
        historyFragment = new HistoryFragment();
        profileFragment = new ProfileFragment();
        setCurrentFragment(homeFragment, true);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mn_home) {
            Toast.makeText(getApplicationContext(), "Buka Home", Toast.LENGTH_SHORT).show();
            setCurrentFragment(homeFragment, true);
        } else if (item.getItemId() == R.id.mn_scan) {
            Toast.makeText(getApplicationContext(), "Buka Scan", Toast.LENGTH_SHORT).show();
            setCurrentFragment(scanAssetFragment, true);
        } else if (item.getItemId() == R.id.mn_history) {
            Toast.makeText(getApplicationContext(), "Buka HIstory", Toast.LENGTH_SHORT).show();
            setCurrentFragment(historyFragment, true);
        } else if (item.getItemId() == R.id.mn_profile) {
            Toast.makeText(getApplicationContext(), "Buka Profile", Toast.LENGTH_SHORT).show();
            setCurrentFragment(profileFragment, true);
        }
        return true;
    }
}