package me.assetnest.assetnest_frontend_android.modul.EditProfile;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class EditProfileActivity extends BaseFragmentHolderActivity {
    EditProfileFragment editProfileFragment;
    @Override
    protected void initializeFragment() {
        initializeView();
        editProfileFragment = new EditProfileFragment();
        setTitle("EditProfile");
        setCurrentFragment(editProfileFragment, false);
    }
}
