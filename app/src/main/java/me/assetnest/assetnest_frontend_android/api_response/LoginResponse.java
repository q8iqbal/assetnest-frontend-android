package me.assetnest.assetnest_frontend_android.api_response;

import me.assetnest.assetnest_frontend_android.model.User;

public class LoginResponse {
    public String token;
    public String token_type;
    public int expires_in;
    public User user;
}
