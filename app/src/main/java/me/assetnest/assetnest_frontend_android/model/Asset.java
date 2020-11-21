package me.assetnest.assetnest_frontend_android.model;

import java.util.Date;

public class Asset {
    private int id;
    private int company_id;
    private int price;
    private String code;
    private String name;
    private String status;
    private String image;
    private String location;
    private String note;
    private String type;
    private String purchase_date;

    public Asset(int id, int company_id, int price, String code, String name, String status, String image, String location, String note, String type, String purchase_date) {
        this.id = id;
        this.company_id = company_id;
        this.price = price;
        this.code = code;
        this.name = name;
        this.status = status;
        this.image = image;
        this.location = location;
        this.note = note;
        this.type = type;
        this.purchase_date = purchase_date;
    }

    public int getId() {
        return id;
    }

    public int getCompany_id() {
        return company_id;
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

    public String getPurchase_date() {
        return purchase_date;
    }
}





