package me.assetnest.assetnest_frontend_android.modul.Profile;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class ProfileActivity extends BaseFragmentHolderActivity {
    ProfileFragment profileFragment;
    @Override
    protected void initializeFragment() {
        initializeView();
        profileFragment = new ProfileFragment();
        setTitle("Profile");
        setCurrentFragment(profileFragment, false);
    }
}
