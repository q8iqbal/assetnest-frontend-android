package me.assetnest.assetnest_frontend_android.modul.profile;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;
import me.assetnest.assetnest_frontend_android.model.Image;
import me.assetnest.assetnest_frontend_android.modul.editProfile.EditProfileActivity;

public class ProfileActivity extends BaseFragmentHolderActivity {
    me.assetnest.assetnest_frontend_android.modul.profile.ProfileFragment profileFragment;
    BottomNavigationView navBar;
    ImageButton btnBack;
    ImageButton btnEdit;

    @Override
    protected void initializeFragment() {
        initializeView();
        profileFragment = new ProfileFragment();
        navBar = findViewById(R.id.bottomNavbar);
        btnBack = findViewById(R.id.btn_back);
        btnEdit = findViewById(R.id.btn_edit);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnEdit.setVisibility(View.VISIBLE);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
                finish();
            }
        });
        navBar.setVisibility(View.GONE);
        setCurrentFragment(profileFragment, false);
    }
}
