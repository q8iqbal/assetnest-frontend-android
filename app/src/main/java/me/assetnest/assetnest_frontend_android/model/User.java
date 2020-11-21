package me.assetnest.assetnest_frontend_android.model;

public class User {
    private String fullname;
    private String company_name;
    private String email;
    private String password;

    public User(String fullname, String company_name, String email, String password) {
        this.fullname = fullname;
        this.company_name = company_name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String password) {
        this.fullname = fullname;
        this.password = password;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
