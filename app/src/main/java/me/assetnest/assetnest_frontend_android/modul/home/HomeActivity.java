package me.assetnest.assetnest_frontend_android.modul.home;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
    @Override
    protected void initializeFragment() {
        initializeView();
        homeFragment = new HomeFragment();
        setCurrentFragment(homeFragment, false);

    }
}
