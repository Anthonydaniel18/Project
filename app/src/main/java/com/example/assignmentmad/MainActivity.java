package com.example.assignmentmad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignmentmad.DB.DB;
import com.example.assignmentmad.DB.DBHELPER;
import com.example.assignmentmad.DB.MODELS.Hotel;
import com.example.assignmentmad.HTTPHANDLER.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    public static Vector<Hotel> hotellist = new Vector<>();
    private String TAG = MainActivity.class.getSimpleName();
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DBHELPER(this);

        //DB.generateDataHotel();
        //DB.generateData();
        //DB.generateDataBooking();

        db = openHelper.getWritableDatabase();

        new muncul().execute();
        Button next = findViewById(R.id.next_txt);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LogIn.class);

                startActivity(login);
            }
        });
    }

    //mulai
    private class muncul extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

                pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Memuat json");
                pd.setCancelable(false);
                pd.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler hh = new HttpHandler();
            String url = "https://bit.ly/34YfE67";
            String jsonstring = hh.panggil(url);

            Log.e(TAG, "JSON : " + jsonstring);

            if (jsonstring != null) {
                try {
                    JSONArray hotel  = new JSONArray(jsonstring);

                    for (int i = 0; i < hotel.length(); i++) {
                        JSONObject h = hotel.getJSONObject(i);

                        String id = h.getString("id");
                        String name = h.getString("name");
                        String address = h.getString("address");
                        String phone_number = h.getString("phone_number");
                        int price_per_night = h.getInt("price_per_night");
                        String LAT = h.getString("LAT");
                        String LNG = h.getString("LNG");
                        String image = h.getString("image");

                        //DB.hotellist.add(new Hotel(id, name, address, phone_number, LAT, LNG, price_per_night,image));
                        db = openHelper.getReadableDatabase();
                        String hitung1 = "SELECT COUNT(*) FROM " + DBHELPER.tabel3;
                        Cursor csr1 = db.rawQuery(hitung1,null);
                        csr1.moveToFirst();
                        int idx1 = csr1.getInt(0);

                        String hitung2 = "SELECT COUNT(*) FROM " + DBHELPER.tabel1;
                        Cursor csr2 = db.rawQuery(hitung2,null);
                        csr2.moveToFirst();
                        int idx2 = csr2.getInt(0);

                        String hitung3 = "SELECT COUNT(*) FROM " + DBHELPER.tabel2;
                        Cursor csr3 = db.rawQuery(hitung3,null);
                        csr3.moveToFirst();
                        int idx3 = csr3.getInt(0);

                        if(idx1<5) {
                            inserthotel(name, address, phone_number, LAT, LNG, Integer.toString(price_per_night), image);
                        }

                        if(idx2<1) {
                            insertmember("New Admin","admin@gmail.com","Pika123","087880373678");
                            insertmember("Lala Luna","lala@gmail.com","Luna345","081362112321");
                        }

                        if(idx3<1) {
                            insertbooking("1","3","Veranda Residence","23/11//2019","24/11/2019","3509000");
                            insertbooking("1","2","Ruru Urban Uma Dewata","24/12/2019","28/12/2019","12105260");
                            insertbooking("2","1","Couleur","24/12/2019","25/12/2019","2896341");
                        }
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json Exception :  " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json error" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "Json gagal");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "No internet", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void hasil) {
            super.onPostExecute(hasil);

            if(pd.isShowing()){
                pd.dismiss();
            }

        }
    }

    void inserthotel(String name, String address, String phone_number, String LAT, String LNG, String price_per_night, String image){
        db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHELPER.h1,name);
        cv.put(DBHELPER.h2,address);
        cv.put(DBHELPER.h3,phone_number);
        cv.put(DBHELPER.h4,LAT);
        cv.put(DBHELPER.h5,LNG);
        cv.put(DBHELPER.h6,price_per_night);
        cv.put(DBHELPER.h7,image);
        db.insert(DBHELPER.tabel3,null,cv);
    }

    void insertbooking(String idmmb, String htlid, String htlname, String cek_in, String cek_out, String totprice){
        ContentValues cv = new ContentValues();
        cv.put(DBHELPER.b1,idmmb);
        cv.put(DBHELPER.b2,htlid);
        cv.put(DBHELPER.b3,htlname);
        cv.put(DBHELPER.b4,cek_in);
        cv.put(DBHELPER.b5,cek_out);
        cv.put(DBHELPER.b6,totprice);
        db.insert(DBHELPER.tabel2,null,cv);
    }

    void insertmember(String name, String email, String pass, String tel){
        openHelper = new DBHELPER(this);
        db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHELPER.m1,name);
        cv.put(DBHELPER.m2,email);
        cv.put(DBHELPER.m3,pass);
        cv.put(DBHELPER.m4,tel);
        db.insert(DBHELPER.tabel1,null,cv);
    }

}
