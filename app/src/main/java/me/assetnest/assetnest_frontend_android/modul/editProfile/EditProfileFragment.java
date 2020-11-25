package me.assetnest.assetnest_frontend_android.modul.editProfile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.util.Objects;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;
import me.assetnest.assetnest_frontend_android.databinding.FragmentEditProfileBinding;
import me.assetnest.assetnest_frontend_android.model.User;
import me.assetnest.assetnest_frontend_android.utils.Constant;

public class EditProfileFragment extends BaseFragment<EditProfileActivity, EditProfileContract.Presenter> implements EditProfileContract.View, View.OnClickListener {

    private FragmentEditProfileBinding binding;
    private File file;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentEditProfileBinding.inflate(inflater , container, false);
        mPresenter = new EditProfilePresenter(this, new EditProfileInteractor());
        mPresenter.start();

        binding.tilDummyPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPasswordEditListener();
            }
        });
        binding.btSave.setOnClickListener(this);
        binding.btEditImage.setOnClickListener(this);
        
        View view = binding.getRoot();
        setTitle("Edit Profile");

        return view;
    }

    @Override
    public void redirectToSuccesEdit() {
        Intent intent = new Intent(activity, EditProfileActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void displayUserData(User user) {
        binding.etEpEmail.setText(user.getEmail());
        binding.etEpName.setText(user.getName());
    }

    @Override
    public void displayUserImage(String path) {
        Glide.with(Objects.requireNonNull(getActivity()))
                .load(Constant.BASE_URL+path)
                .placeholder(R.drawable.editprofile_profile)
                .into(binding.ivProfile);
    }

    @Override
    public void startLoading() {
        binding.pbEditProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.pbEditProfile.setVisibility(View.GONE);
    }

    @Override
    public void showMassage(String massage) {
        Toast.makeText(getActivity(), massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btSave.getId()){
            onSaveButtonClick();
        }

        if (v.getId() == binding.btEditImage.getId()){
            onEditButtonClick();
        }
    }

    @Override
    public void onEditButtonClick() {
        ImagePicker.Companion.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    @Override
    public void onPasswordEditListener() {
        binding.tilDummyPassword.setVisibility(View.INVISIBLE);
        binding.tilPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveButtonClick(){
        if(file != null){
            this.uploadImage();
        }else {
            this.updateData("");
        }
    }

    @Override
    public void uploadImage() {
        mPresenter.performUploadImage(file);
    }

    @Override
    public void updateData(String path) {
        String email = Objects.requireNonNull(binding.etEpEmail.getText()).toString();
        String name = Objects.requireNonNull(binding.etEpName.getText()).toString();
        String password = Objects.requireNonNull(binding.etPassword.getText()).toString();
        mPresenter.performEditProfile(name,email,path,password);
    }

    @Override
    public void setPresenter(EditProfilePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            binding.ivProfile.setImageURI(uri);
            file = ImagePicker.Companion.getFile(data);
        }else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getActivity(), ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
