package me.assetnest.assetnest_frontend_android.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String image;
    private String role;
    private int companyId;

    public User(int id,String name, String email, String image, String role, int companyId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.image = image;
        this.role = role;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public String getRole() {
        return role;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int geId(){ return id; }
}
