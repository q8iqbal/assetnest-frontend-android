package me.assetnest.assetnest_frontend_android.model;

import java.util.Date;

public class Asset {
    private int id;
    private int companyId;
    private int price;
    private String code;
    private String name;
    private String status;
    private String image;
    private String location;
    private String note;
    private String type;
    private String purchaseDate;

    public Asset(int id, int companyId, int price, String code, String name, String status, String image, String location, String note, String type, String purchaseDate) {
        this.id = id;
        this.companyId = companyId;
        this.price = price;
        this.code = code;
        this.name = name;
        this.status = status;
        this.image = image;
        this.location = location;
        this.note = note;
        this.type = type;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
}





