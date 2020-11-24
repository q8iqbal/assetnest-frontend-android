package me.assetnest.assetnest_frontend_android.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;
import me.assetnest.assetnest_frontend_android.model.User;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;

public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.Presenter> implements ProfileContract.View {

    TextView tvName;
    TextView tvCompany;
    TextView tvEmail;
    Button btnSignOut;
    User user;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        mPresenter = new me.assetnest.assetnest_frontend_android.modul.profile.ProfilePresenter(this, new ProfileInteractor(UtilProvider.getSharedPreferenceUtil()));
        mPresenter.start();
        initView();
        mPresenter.getUser();

<<<<<<< HEAD
        setTitle("Profile");
=======
//        btnSignOut = fragmentView.findViewById(R.id.);
//        btnSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setBtLoginClick();
//            }
//        });

        setTitle("PROFILE");
>>>>>>> chandra2
        return fragmentView;
    }

    public void initView(){
        tvName = fragmentView.findViewById(R.id.tv_fullname_value);
        tvCompany = fragmentView.findViewById(R.id.tv_company_name_value);
        tvEmail = fragmentView.findViewById(R.id.tv_email_value);
        btnSignOut = fragmentView.findViewById(R.id.sign_out);
    }


    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProfile(User user) {
        tvName.setText(user.getName());
        tvCompany.setText("company dg id : "+user.getCompany_id());
        tvEmail.setText(user.getEmail());
    }
}
