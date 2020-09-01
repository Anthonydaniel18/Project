package com.example.assignmentmad.DB.MODELS;

public class Hotel {

    public String htlid, htlname, htlads, htlphn, latitude, longitude, image;
    public int price;

    public Hotel(String htlid, String htlname, String htlads, String htlphn, String latitude, String longitude, int price, String image) {
        this.htlid = htlid;
        this.htlname = htlname;
        this.htlads = htlads;
        this.htlphn = htlphn;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.image = image;
    }

    public String getHtlid() {
        return htlid;
    }

    public void setHtlid(String htlid) {
        this.htlid = htlid;
    }

    public String getHtlname() {
        return htlname;
    }

    public void setHtlname(String htlname) {
        this.htlname = htlname;
    }

    public String getHtlads() {
        return htlads;
    }

    public void setHtlads(String htlads) {
        this.htlads = htlads;
    }

    public String getHtlphn() {
        return htlphn;
    }

    public void setHtlphn(String htlphn) {
        this.htlphn = htlphn;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage(){return image;}

    public void setImage(String image){this.image = image;}
}
