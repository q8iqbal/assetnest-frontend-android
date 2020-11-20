package me.assetnest.assetnest_frontend_android.modul.Profile;

import me.assetnest.assetnest_frontend_android.modul.add_task.AddTaskFragment;

public class ProfilePresenter implements ProfileContract.Presenter{
    private final ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performEditTask(){
        view.redirectToSuccesEdit();
    }
}
