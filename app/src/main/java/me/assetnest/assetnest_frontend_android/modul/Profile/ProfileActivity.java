package me.assetnest.assetnest_frontend_android.modul.profile;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class ProfileActivity extends BaseFragmentHolderActivity {
    me.assetnest.assetnest_frontend_android.modul.profile.ProfileFragment profileFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        profileFragment = new ProfileFragment();
        setCurrentFragment(profileFragment, false);
    }
}
