package me.assetnest.assetnest_frontend_android.modul.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import me.assetnest.assetnest_frontend_android.api_response.GetCompanyResponse;
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
                            requestCompany();
                        }else {
                            requestCallback.requestFailed("Invalid Credential");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 406){
                            requestCallback.requestFailed("Invalid Credential");
                        }
                        requestCallback.requestFailed("Server Offline");
                    }
                });
    }

    @Override
    public void requestCompany() {
        AndroidNetworking.get(Constant.BASE_URL+"companies")
                .addHeaders("Authorization", getToken())
                .build()
                .getAsObject(GetCompanyResponse.class, new ParsedRequestListener<GetCompanyResponse>() {
                    @Override
                    public void onResponse(GetCompanyResponse response) {
                        sharedPreferenceUtil.setCompany(new Gson().toJson(response.data));
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("opppai", anError.getErrorBody());
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

    @NonNull
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
