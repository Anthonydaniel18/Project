package com.example.assignmentmad.DB.MODELS;

public class Booking {

    public String bkid, mmbid, htlid, htlname, strdate, enddate;
    public int price;

    public Booking(String bkid, String mmbid, String htlid, String htlname, String strdate, String enddate, int price){
    //public Booking(String bkid, String htlname, String strdate, String enddate, int price) {
        this.bkid = bkid;
        this.mmbid = mmbid;
        this.htlid = htlid;
        this.htlname = htlname;
        this.strdate = strdate;
        this.enddate = enddate;
        this.price = price;
    }

    public String getBkid() {
        return bkid;
    }

    public void setBkid(String bkid) {
        this.bkid = bkid;
    }

    public String getMmbid() {return mmbid; }

    public void setMmbid(String mmbid) {this.mmbid = mmbid; }

    public String getHtlid() {return htlid; }

    public void setHtlid(String htlid) {this.htlid = htlid; }

    public String getHtlname(){return htlname;}

    public void setHtlname(){this.htlname=htlname;}

    public String getStrdate() {
        return strdate;
    }

    public void setStrdate(String strdate) {
        this.strdate = strdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
