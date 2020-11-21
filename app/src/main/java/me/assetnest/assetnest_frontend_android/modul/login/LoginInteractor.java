package me.assetnest.assetnest_frontend_android.modul.login;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import me.assetnest.assetnest_frontend_android.api_response.LoginResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class LoginInteractor implements LoginContract.Interactor {
    private SharedPreferenceUtil sharedPreferenceUtil;

    public LoginInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
    }


    @Override
    public void requestLogin(String email, String password,final RequestCallback<LoginResponse> requestCallback) {
        JSONObject userObj = createUserJson(email, password);

        AndroidNetworking.post(Constant.BASE_URL+"login/mobile")
                .addJSONObjectBody(userObj)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("null response");
                        }else if(!(response.token.isEmpty())){
                            requestCallback.requestSuccess(response);
                        }else {
                            requestCallback.requestFailed("Invalid Credential");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Server Offline");
                    }
                });
    }

    @Override
    public void saveToken(String token) {
        sharedPreferenceUtil.setToken(token);
    }

    @Override
    public String getToken() {
        return sharedPreferenceUtil.getToken();
    }

    @Override
    public void saveUser(String user) { sharedPreferenceUtil.setUser(user); }

    @NotNull
    private JSONObject createUserJson(String email, String password){
        JSONObject user = new JSONObject();
        JSONObject userObj = new JSONObject();
        try{
            user.put("email", email);
            user.put("password", password);
            userObj.put("user",user);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return  userObj;
    }
}
