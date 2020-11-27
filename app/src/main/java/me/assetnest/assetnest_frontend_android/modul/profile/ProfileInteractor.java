package me.assetnest.assetnest_frontend_android.modul.profile;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import me.assetnest.assetnest_frontend_android.api_response.LoginResponse;
import me.assetnest.assetnest_frontend_android.api_response.LogoutResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class ProfileInteractor implements ProfileContract.Interactor{
    private SharedPreferenceUtil sharedPreferenceUtil;
    private String token;
    ProfileInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
        token = sharedPreferenceUtil.getToken();
    }

    @Override
    public String getUser() {
        return sharedPreferenceUtil.getUser();
    }

    @Override
    public String getCompany() {
        return sharedPreferenceUtil.getCompany();
    }

    @Override
    public void signOut(final RequestCallback<LogoutResponse> responseCallback) {
        AndroidNetworking.get(Constant.BASE_URL + "logout")
                .addHeaders("Authorization", token)
                .build()
                .getAsObject(LogoutResponse.class, new ParsedRequestListener<LogoutResponse>() {
                    @Override
                    public void onResponse(LogoutResponse response) {
                        // do anything with response
                        Log.d("TESAPI", "respone e = "+response.status);
                        if(response == null){
                            responseCallback.requestFailed("null response");
                        }else if(response.status.equals("success")){
                            responseCallback.requestSuccess(response);
                        }else {
                            responseCallback.requestFailed("Invalid Credential");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        // handle error
                    }
                });
    }

    @Override
    public void clearDataUser() {
        sharedPreferenceUtil.clear();
    }


}
