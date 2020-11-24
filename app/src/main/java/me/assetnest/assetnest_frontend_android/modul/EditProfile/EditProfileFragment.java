package me.assetnest.assetnest_frontend_android.modul.editProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;

public class EditProfileFragment extends BaseFragment<EditProfileActivity, EditProfileContract.Presenter> implements EditProfileContract.View {

    EditText etFullName;
    EditText etPassword;
    Button btnSave;

    public EditProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        mPresenter = new me.assetnest.assetnest_frontend_android.modul.editProfile.EditProfilePresenter(this);
        mPresenter.start();

        btnSave = fragmentView.findViewById(R.id.save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtLoginClick();
            }
        });

        setTitle("EDIT PROFILE");
        return fragmentView;
    }

    public void setBtLoginClick(){
        mPresenter.performEditProfile();
    }

    @Override
    public void setPresenter(EditProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToSuccesEdit() {
        Intent intent = new Intent(activity, EditProfileActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
