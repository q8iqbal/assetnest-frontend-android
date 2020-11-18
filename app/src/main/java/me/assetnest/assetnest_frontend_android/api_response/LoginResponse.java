package me.assetnest.assetnest_frontend_android.api_response;

import me.assetnest.assetnest_frontend_android.model.User;

public class LoginResponse {
    private String token;
    private String tokenType;
    private int expires;
    private User user;

    public LoginResponse(String token, String tokenType, int expires, User user) {
        this.token = token;
        this.tokenType = tokenType;
        this.expires = expires;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpires() {
        return expires;
    }

    public User getUser() {
        return user;
    }
}
