package me.assetnest.assetnest_frontend_android.modul.profile;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class ProfileActivity extends BaseFragmentHolderActivity {
<<<<<<< HEAD
    me.assetnest.assetnest_frontend_android.modul.profile.ProfileFragment profileFragment;
=======
    me.assetnest.assetnest_frontend_android.modul.Profile.ProfileFragment profileFragment;
    private final int UPDATE_REQUEST = 2019;
>>>>>>> chandra2

    @Override
    protected void initializeFragment() {
        initializeView();
        profileFragment = new ProfileFragment();
        setCurrentFragment(profileFragment, false);
    }
}
