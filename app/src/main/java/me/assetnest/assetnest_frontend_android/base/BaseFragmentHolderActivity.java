package me.assetnest.assetnest_frontend_android.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.modul.history.HistoryActivity;
import me.assetnest.assetnest_frontend_android.modul.home.HomeActivity;
import me.assetnest.assetnest_frontend_android.modul.profile.ProfileActivity;
import me.assetnest.assetnest_frontend_android.modul.scanasset.ScanAssetActivity;

public abstract class BaseFragmentHolderActivity extends BaseActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected RelativeLayout rlActivityFragmentHolder;

    @Override
    protected void initializeFragment() {

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    public void setTitle(String title) {
        this.tvToolbarTitle.setText(title);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mn_home) {
            Toast.makeText(getApplicationContext(), "Buka Home", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        } else if (item.getItemId() == R.id.mn_scan) {
            Toast.makeText(getApplicationContext(), "Buka Scan", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, ScanAssetActivity.class));
        } else if (item.getItemId() == R.id.mn_history) {
            Toast.makeText(getApplicationContext(), "Buka HIstory", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, HistoryActivity.class));
        } else if (item.getItemId() == R.id.mn_profile) {
            Toast.makeText(getApplicationContext(), "Buka Profile", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
        return false;
    }
}
