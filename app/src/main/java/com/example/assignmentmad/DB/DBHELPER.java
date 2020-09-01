package com.example.assignmentmad.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHELPER extends SQLiteOpenHelper {

    public static final String namaDatabase = "HotelBooking";

    public static final String tabel1 = "Member";
    public static final String m1 = "name";
    public static final String m2 = "email";
    public static final String m3 = "pass";
    public static final String m4 = "tel";

    public static final String tabel2 = "Booking";
    public static final String b1 = "memid";
    public static final String b2 = "htlid";
    public static final String b3 = "htlname";
    public static final String b4 = "start";
    public static final String b5 = "finish";
    public static final String b6 = "price";
    public static final String b7 = "id";

    public static final String tabel3 = "Hotel";
    public static final String h1 = "htlname";
    public static final String h2 = "htladdress";
    public static final String h3 = "htltel";
    public static final String h4 = "htllat";
    public static final String h5 = "htllng";
    public static final String h6 = "htlprice";
    public static final String h7 = "htlimage";

    public DBHELPER(@Nullable Context context) {
        super(context, namaDatabase, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querymember = "Create Table " + tabel1 +
                "(id integer primary key autoincrement, name text, email text, pass text, tel text)";

        String querybooking = "Create Table " + tabel2 +
                "(id integer primary key autoincrement, memid text, htlid text, htlname text, start text, finish text, price text)";

        String queryhotel = "Create Table " + tabel3 +
                "(id integer primary key autoincrement, htlname text, htladdress text, htltel text, htllat text, htllng text, htlprice text, htlimage text)";

        db.execSQL(queryhotel);
        db.execSQL(querymember);
        db.execSQL(querybooking);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tabel1);
        db.execSQL("Drop table if exists " + tabel2);
        db.execSQL("Drop table if exists " + tabel3);
        onCreate(db);
    }
}
