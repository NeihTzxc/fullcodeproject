package com.example.thuchicanhan;

public class lv_item_thu {
    String title, ngay, tien,danhmuc;

    public lv_item_thu(String title, String ngay, String tien,String danhmuc) {
        this.title = title;
        this.ngay = ngay;
        this.tien = tien;
        this.danhmuc=danhmuc;
    }

    public lv_item_thu(String danhmuc) {
        this.danhmuc = danhmuc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }
}
