package me.assetnest.assetnest_frontend_android.model;

public class User {
    private int id;
    private int company_id;
    private String name;
    private String email;
    private String image;
    private String role;
    private String password;
    private String deleted_at;

    public User(int id, int company_id, String name, String email, String image, String role, String password, String deleted_at) {
        this.id = id;
        this.company_id = company_id;
        this.name = name;
        this.email = email;
        this.image = image;
        this.role = role;
        this.password = password;
        this.deleted_at = deleted_at;
    }

    public User(String name, String email, String image) {
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public User(String name, String email, String image, String password) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public int getCompany_id() {
        return company_id;
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

    public String getPassword() {
        return password;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

}
