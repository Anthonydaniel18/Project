package com.example.assignmentmad.DB.MODELS;

public class Member {

    public String mmbname, mmbemail, mmbpass, mmbtel;
    public int mmbid;

    public Member(int mmbid, String mmbname, String mmbemail, String mmbpass, String mmbtel){

        this.mmbid = mmbid;
        this.mmbname = mmbname;
        this.mmbemail = mmbemail;
        this.mmbpass = mmbpass;
        this.mmbtel = mmbtel;

    }

    public int getMmbid() {
        return mmbid;
    }

    public void setMmbid(int mmbid) {
        this.mmbid = mmbid;
    }

    public String getMmbname() {
        return mmbname;
    }

    public void setMmbname(String mmbname) {
        this.mmbname = mmbname;
    }

    public String getMmbemail() {
        return mmbemail;
    }

    public void setMmbemail(String mmbemail) {
        this.mmbemail = mmbemail;
    }

    public String getMmbpass() {
        return mmbpass;
    }

    public void setMmbpass(String mmbpass) {
        this.mmbpass = mmbpass;
    }

    public String getMmbtel() {
        return mmbtel;
    }

    public void setMmbtel(String mmbtel) {
        this.mmbtel = mmbtel;
    }
}
