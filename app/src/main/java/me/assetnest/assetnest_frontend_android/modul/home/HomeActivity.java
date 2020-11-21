package me.assetnest.assetnest_frontend_android.modul.home;

import android.view.View;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
    @Override
    protected void initializeFragment() {
        initializeView();
        homeFragment = new HomeFragment();
        setTitle("Home");
        setCurrentFragment(homeFragment, false);
    }
}
