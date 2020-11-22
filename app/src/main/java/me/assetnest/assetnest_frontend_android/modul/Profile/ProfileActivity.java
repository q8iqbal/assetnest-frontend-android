package me.assetnest.assetnest_frontend_android.modul.Profile;

import android.view.View;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class ProfileActivity extends BaseFragmentHolderActivity {
    me.assetnest.assetnest_frontend_android.modul.Profile.ProfileFragment profileFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();
//        btBack.setVisibility(View.GONE);
//        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setVisibility(View.VISIBLE);

        profileFragment = new ProfileFragment();
        setCurrentFragment(profileFragment, false);
    }
}
