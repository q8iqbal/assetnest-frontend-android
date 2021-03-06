package me.assetnest.assetnest_frontend_android.utils;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    private SharedPreferences sharedPreferences;

    private static final String TOKEN = "jwt_token";
    private static final String USER = "user";
    private static final String COMPANY = "company";

    public SharedPreferenceUtil(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public void setToken(String token){
        sharedPreferences.edit().putString(TOKEN, token).apply();
    }

    public String getToken(){
        return sharedPreferences.getString(TOKEN, null);
    }

    public void setUser(String user){
        sharedPreferences.edit().putString(USER, user).apply();
    }

    public String getUser() { return sharedPreferences.getString(USER, null); }

    public void setCompany(String company){
        sharedPreferences.edit().putString(COMPANY, company).apply();
    }

    public String getCompany() { return sharedPreferences.getString(COMPANY, null); }

    public void clear(){
        sharedPreferences.edit().clear().apply();
    }
}
