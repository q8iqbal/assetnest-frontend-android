package me.assetnest.assetnest_frontend_android.modul.EditProfile;

import android.view.View;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class EditProfileActivity extends BaseFragmentHolderActivity {
    me.assetnest.assetnest_frontend_android.modul.EditProfile.EditProfileFragment editProfileFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();
        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        editProfileFragment = new EditProfileFragment();
        setCurrentFragment(editProfileFragment, false);
    }
}
