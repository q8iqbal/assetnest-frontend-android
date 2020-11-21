package me.assetnest.assetnest_frontend_android.modul.EditProfile;

import me.assetnest.assetnest_frontend_android.modul.EditProfile.EditProfileFragment;

public class EditProfilePresenter implements EditProfileContract.Presenter{
    private final EditProfileContract.View view;

    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performEditProfile(){
        view.redirectToSuccesEdit();
    }
}
