package me.assetnest.assetnest_frontend_android.modul.editProfile;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;

import me.assetnest.assetnest_frontend_android.api_response.PostUserImage;
import me.assetnest.assetnest_frontend_android.api_response.PutUserResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.User;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;

public class EditProfileInteractor implements EditProfileContract.Interactor {
    private final SharedPreferenceUtil sharedPreferenceUtil = UtilProvider.getSharedPreferenceUtil();


    @Override
    public void requestUpdateUser(int id, JSONObject user, final RequestCallback<PutUserResponse> requestCallback) {
        AndroidNetworking.put(Constant.BASE_URL+"users/"+id)
                .addHeaders("Authorization", sharedPreferenceUtil.getToken())
                .addJSONObjectBody(user)
                .build()
                .getAsObject(PutUserResponse.class, new ParsedRequestListener<PutUserResponse>() {
                    @Override
                    public void onResponse(PutUserResponse response) {
                        if(response.status != null){
                            requestCallback.requestSuccess(response);
                        }else {
                            sharedPreferenceUtil.clear();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getErrorBody());
                        Log.e("TAG", "onError: "+anError.getErrorBody() );
                    }
                });

    }

    @Override
    public void requestUploadUserImage(File file, final RequestCallback<PostUserImage> requestCallback) {
        AndroidNetworking.upload(Constant.BASE_URL+"users/image")
                .addHeaders("Authorization", sharedPreferenceUtil.getToken())
                .addMultipartFile("image", file)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(PostUserImage.class, new ParsedRequestListener<PostUserImage>() {
                    @Override
                    public void onResponse(PostUserImage response) {
                        requestCallback.requestSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("failed to upload image");
                    }
                });
    }

    @Override
    public void saveNewUser(String user) {
        sharedPreferenceUtil.setUser(user);
    }

    @Override
    public User getUserData() {
        User user = new Gson().fromJson(sharedPreferenceUtil.getUser(),User.class);
        return user;
    }
}
