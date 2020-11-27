package me.assetnest.assetnest_frontend_android.modul.editProfile;

import org.json.JSONObject;

import java.io.File;

import me.assetnest.assetnest_frontend_android.api_response.PostUserImage;
import me.assetnest.assetnest_frontend_android.api_response.PutUserResponse;
import me.assetnest.assetnest_frontend_android.base.BasePresenter;
import me.assetnest.assetnest_frontend_android.base.BaseView;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.User;

public interface EditProfileContract {
    interface View extends BaseView<EditProfilePresenter> {
        void redirectToSuccesEdit();
        void displayUserData(User user);
        void displayUserImage(String path);

        void startLoading();
        void endLoading();

        void showMassage(String massage);

        void onEditButtonClick();

        void onPasswordEditListener();
        void onSaveButtonClick();
        void uploadImage();
        void updateData(String path);
    }

    interface Presenter extends BasePresenter {
        void performEditProfile(String name, String email, String path, String password);
        void performUploadImage(File file);
    }

    interface Interactor {
        void requestUpdateUser(int id,JSONObject user, RequestCallback<PutUserResponse> requestCallback);
        void requestUploadUserImage(File file, RequestCallback<PostUserImage> requestCallback);
        void saveNewUser(String user);
        User getUserData();
    }
}