package me.assetnest.assetnest_frontend_android.modul.Profile;

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

public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.Presenter> implements ProfileContract.View {

    EditText etEmail;
    EditText etPassword;
    Button btnSignOut;
    String TAG = "TES_API";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYXNzZXRuZXN0Lm1lXC9sb2dpblwvbW9iaWxlIiwiaWF0IjoxNjA2MDc2MTkxLCJuYmYiOjE2MDYwNzYxOTEsImp0aSI6ImU1aTFUdjRudVFQREhCU1EiLCJzdWIiOjYsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.4FFzXe-0xn8gSoYNNJrCtmE_Z1n1CGwRblkOtsypQQY";

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        mPresenter = new me.assetnest.assetnest_frontend_android.modul.Profile.ProfilePresenter(this);
        mPresenter.start();

        btnSignOut = fragmentView.findViewById(R.id.sign_out);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtLoginClick();
            }
        });

        setTitle("PROFILE");
        return fragmentView;
    }

    public void getDataFromAPI(){

    }

    public void setBtLoginClick(){
        mPresenter.performEditTask();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToSuccesEdit() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
