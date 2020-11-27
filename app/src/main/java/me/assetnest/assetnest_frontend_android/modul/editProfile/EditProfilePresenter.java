package me.assetnest.assetnest_frontend_android.modul.editProfile;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import me.assetnest.assetnest_frontend_android.api_response.PostUserImage;
import me.assetnest.assetnest_frontend_android.api_response.PutUserResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.User;

public class EditProfilePresenter implements EditProfileContract.Presenter, RequestCallback{
    private final EditProfileContract.View view;
    private final EditProfileContract.Interactor interactor;
    private User user;

    public EditProfilePresenter(EditProfileContract.View view, EditProfileContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
        user = interactor.getUserData();
    }

    @Override
    public void start() {
        view.displayUserData(user);
        view.displayUserImage(user.getImage());
    }


    @Override
    public void performEditProfile(String name, String email, String path, String password) {
        view.startLoading();
        JSONObject userJson = createUserJson(name,email,password,path);
        interactor.requestUpdateUser(user.getId() ,userJson, new RequestCallback<PutUserResponse>() {
            @Override
            public void requestSuccess(PutUserResponse data) {
                interactor.saveNewUser(new Gson().toJson(data.data));
                view.endLoading();
                view.showMassage("data updated");
                view.redirectToSuccesEdit();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showMassage("network error");
            }
        });
    }

    @Override
    public void performUploadImage(File file) {
        view.startLoading();
        RequestCallback<PostUserImage> callback = new RequestCallback<PostUserImage>(){
            @Override
            public void requestSuccess(PostUserImage data) {
                view.updateData(data.data.getPath());
                Log.e("TAG", "requestSuccess: "+data.data.getPath() );
                view.endLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
            }
        };

        interactor.requestUploadUserImage(file, callback);
    }

    private JSONObject createUserJson(String name, String email, String password , String path){
        JSONObject user = new JSONObject();
        JSONObject userObj = new JSONObject();
        try{
            user.put("name", name);
            user.put("email", email);
            user.put("role", this.user.getRole());
            user.put("image", this.user.getImage());

            if(!path.isEmpty())
                user.put("image", path);
            if(!password.isEmpty())
                user.put("password", password);

            userObj.put("user",user);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return  userObj;
    }

    @Override
    public void requestSuccess(Object data) {

    }

    @Override
    public void requestFailed(String errorMessage) {

    }
}
