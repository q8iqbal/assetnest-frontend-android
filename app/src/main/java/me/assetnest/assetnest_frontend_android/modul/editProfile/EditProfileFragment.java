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
import me.assetnest.assetnest_frontend_android.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends BaseFragment<EditProfileActivity, EditProfileContract.Presenter> implements EditProfileContract.View, View.OnClickListener {

    EditText etFullName;
    EditText etPassword;
    Button btnSave;

    private FragmentEditProfileBinding binding;

    public EditProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentEditProfileBinding.inflate(inflater , container, false);
        mPresenter = new EditProfilePresenter(this);
        mPresenter.start();
//        binding.btSave.setOnClickListener(this);

        View view = binding.getRoot();
        setTitle("EDIT PROFILE");

        return view;
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

    @Override
    public void onClick(View v) {
//        if(v.getId() == binding.btSave.getId()){
//            onButtonLoginClick();
//        }
    }

    public void onButtonLoginClick(){
        mPresenter.performEditProfile();
    }
}
