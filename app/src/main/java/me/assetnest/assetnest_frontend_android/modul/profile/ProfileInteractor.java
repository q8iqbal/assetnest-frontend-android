package me.assetnest.assetnest_frontend_android.modul.profile;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import me.assetnest.assetnest_frontend_android.api_response.AssetResponse;
import me.assetnest.assetnest_frontend_android.callback.RequestCallback;
import me.assetnest.assetnest_frontend_android.model.Company;
import me.assetnest.assetnest_frontend_android.model.User;
import me.assetnest.assetnest_frontend_android.utils.Constant;
import me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil;

public class ProfileInteractor implements ProfileContract.Interactor{
    private SharedPreferenceUtil sharedPreferenceUtil;

    ProfileInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
    }

    @Override
    public String getUser() {
        return sharedPreferenceUtil.getUser();
    }

    @Override
    public void requestCompany(final RequestCallback<Company> requestCallback) {
        User user = new Gson().fromJson(getUser(), User.class);
        AndroidNetworking.get(Constant.SHOW_COMPANY + "/{id}")
                .addPathParameter("id", Integer.toString(user.getId()))
                .addHeaders("Authorization", token)
                .build()
                .getAsObject(AssetResponse.class, new ParsedRequestListener<CompanyResponse>() {
                    @Override
                    public void onResponse(CompanyResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response.data);
                        }
                        else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }
}
