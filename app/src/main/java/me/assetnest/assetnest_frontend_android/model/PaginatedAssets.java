package me.assetnest.assetnest_frontend_android.model;

import java.util.List;

public class PaginatedAssets {
    private int last_page;
    private List<Asset> data;

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<Asset> getData() {
        return data;
    }

    public void setData(List<Asset> data) {
        this.data = data;
    }
}
