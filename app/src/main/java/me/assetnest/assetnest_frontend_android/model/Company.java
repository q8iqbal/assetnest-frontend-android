package me.assetnest.assetnest_frontend_android.model;

public class Company {
    private int id;
    private String name;
    private String address;
    private String image;
    private String description;
    private String phone;

    public Company(int id, String name, String address, String image, String description, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }
}
