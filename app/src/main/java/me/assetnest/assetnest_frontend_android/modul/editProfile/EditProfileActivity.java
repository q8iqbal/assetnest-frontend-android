package me.assetnest.assetnest_frontend_android.modul.editProfile;

import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class EditProfileActivity extends BaseFragmentHolderActivity {
    private EditProfileFragment editProfileFragment;
    BottomNavigationView navBar;
    ImageButton btnBack;

    @Override
    protected void initializeFragment() {
        initializeView();
        navBar = findViewById(R.id.bottomNavbar);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setVisibility(View.VISIBLE);
        navBar.setVisibility(View.GONE);
        editProfileFragment = new EditProfileFragment();
        setCurrentFragment(editProfileFragment, false);
    }
}
