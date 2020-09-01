package com.example.assignmentmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmentmad.DB.DB;
import com.example.assignmentmad.DB.DBHELPER;
import com.example.assignmentmad.DB.MODELS.Booking;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HotelDetail extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, OnMapReadyCallback {

    TextView name, phone, ads, latitude, longitude, price, cekin, cekout;
    Button cekinbtn, cekoutbtn, bookbtn;
    String htlid, htlname, htlads, htlphn, htllat,htllong, idmmb, image,tel;
    SimpleDateFormat dateFormat;
    Date datein, dateout;
    Long miliin, miliout, diffdate;
    int totprice,coba, idbk, year, month, day, year1, month1, day1,htlprice,izin;
    Calendar calendarcekin, calendarcekout;
    Double lat,lng;
    private GoogleMap mMap;
    ImageView gambar;
    SmsManager smsm;
    View view;

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    DatePickerDialog datepick, datepick1;
    String cek_in, cek_out;

    void init(){
        name = findViewById(R.id.hotelname);
        phone = findViewById(R.id.hotelphone);
        ads = findViewById(R.id.hoteladdress);
        //latitude = findViewById(R.id.hotellatitude);
        //longitude = findViewById(R.id.hotelllongitude);
        price = findViewById(R.id.hotelprice);
        cekin = findViewById(R.id.cekin);
        cekout = findViewById(R.id.cekout);
        //gambar = findViewById(R.id.gambarHotel2);

        cekinbtn = findViewById(R.id.cekinbtn);
        cekoutbtn = findViewById(R.id.cekoutbtn);
        bookbtn = findViewById(R.id.bookbtn);

        SupportMapFragment map = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);

    }

    void itungprice(){
        try{
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            datein = dateFormat.parse(cek_in);
            dateout = dateFormat.parse(cek_out);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        idbk = DB.bookinglist.size()+1;

        miliin = datein.getTime();
        miliout = dateout.getTime();
        diffdate = (miliout - miliin) / (1000*60*60*24);
        coba = Math.round(diffdate);
        totprice = coba * htlprice;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        init();

        openHelper = new DBHELPER(this);
        db = openHelper.getWritableDatabase();

        Intent getIntent = getIntent();
        htlid = getIntent.getStringExtra("idhotel");
        htlname = getIntent.getStringExtra("hotelname");
        htlads = getIntent.getStringExtra("hotelads");
        htlphn = getIntent.getStringExtra("hotelphone");
        htllat = getIntent.getStringExtra("hotellat");
        htllong = getIntent.getStringExtra("hotellong");
        htlprice = getIntent.getIntExtra("hotelprice",0);
        idmmb = getIntent.getStringExtra("idmember");
        image = getIntent.getStringExtra("hotelimage");
        tel = getIntent.getStringExtra("notel");

        name.setText(htlname);
        phone.setText(htlphn);
        ads.setText(htlads);
        //latitude.setText(htllat);
        //longitude.setText(htllong);
        price.setText(Integer.toString(htlprice));
        //Picasso.get().load(image).into(gambar);

        lat = Double.valueOf(htllat);
        lng = Double.valueOf(htllong);

        calendarcekin = Calendar.getInstance();
        day = calendarcekin.get(Calendar.DAY_OF_MONTH);
        month = calendarcekin.get(Calendar.MONTH);
        year = calendarcekin.get(Calendar.YEAR);

        cek_in = day + "/" + (month + 1) + "/" + year;

        calendarcekout = Calendar.getInstance();
        calendarcekout.add(Calendar.DAY_OF_MONTH, 1);
        day1 = calendarcekout.get(Calendar.DAY_OF_MONTH);
        month1 = calendarcekout.get(Calendar.MONTH);
        year1 = calendarcekin.get(Calendar.YEAR);

        cek_out = day1 + "/" + (month1 + 1) + "/" + year1;

        cekinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                datepick = new DatePickerDialog(HotelDetail.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {
                        cek_in = hari + "/"+ (bulan+1)+"/" +tahun;
                        cekin.setText(cek_in);

                        itungprice();
                        price.setText(Integer.toString(totprice));
                    }
                },year,month,day);
                datepick.show();
            }
        });

        cekoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year1 = c.get(Calendar.YEAR);
                month1 = c.get(Calendar.MONTH);
                day1 = c.get(Calendar.DAY_OF_MONTH);

                datepick1 = new DatePickerDialog(HotelDetail.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {
                        cek_out = hari + "/"+ (bulan+1)+"/" +tahun;
                        cekout.setText(cek_out);

                        itungprice();
                        price.setText(Integer.toString(totprice));
                    }
                },year1,month1,day1);
                datepick1.show();
            }
        });

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(miliin != null){
                if(((miliin - (Calendar.getInstance().getTimeInMillis())) / (1000 * 60 * 60 * 24)) < 0){
                    Toast.makeText(HotelDetail.this, "Check in must be after today", Toast.LENGTH_SHORT).show();
                }else if(diffdate<1){
                    Toast.makeText(HotelDetail.this, "Check out must be after check in", Toast.LENGTH_SHORT).show();
                }else {
                    //DB.addBooking(Integer.toString(idbk), idmmb, htlid, cek_in, cek_out, totprice);
                    //DB.addBooking(Integer.toString(idbk),idmmb,htlid,htlname,cek_in,cek_out,totprice);
                    insertbooking(idmmb, htlid, htlname, cek_in, cek_out, Integer.toString(totprice));

                    sms(v);

                    String test_number  =("5554");
                    String message = htlname + htlads + cek_in + cek_out + totprice;

                    if(tel.isEmpty()){
                        Toast.makeText(HotelDetail.this,"nomor tujuan",Toast.LENGTH_SHORT).show();
                    }else if(message.isEmpty()){
                        Toast.makeText(HotelDetail.this,"pesan",Toast.LENGTH_SHORT).show();
                    }else{
                        smsm.sendTextMessage(tel,null,message,null,null);
                    }

                    Intent enter = new Intent(HotelDetail.this, HomePage.class);
                    startActivity(enter);
                }
                }
            }
        });

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap=googleMap;

        LatLng hotel = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(hotel));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hotel));

    }

    public void sms(View view){
        smsm = SmsManager.getDefault();

        izin = ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS);
        if(izin!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        }
    }

}
