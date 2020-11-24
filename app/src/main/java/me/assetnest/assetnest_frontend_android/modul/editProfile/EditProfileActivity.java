package me.assetnest.assetnest_frontend_android.modul.editProfile;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class EditProfileActivity extends BaseFragmentHolderActivity {
    private EditProfileFragment editProfileFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        editProfileFragment = new EditProfileFragment();
        setCurrentFragment(editProfileFragment, false);
    }
}
