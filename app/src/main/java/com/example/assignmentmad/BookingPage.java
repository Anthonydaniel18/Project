package com.example.assignmentmad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignmentmad.ADAPTERS.BookingAdapter;
import com.example.assignmentmad.DB.DB;
import com.example.assignmentmad.DB.MODELS.Booking;
import com.example.assignmentmad.DB.MODELS.Hotel;

import java.util.Vector;

public class BookingPage extends AppCompatActivity {

    ListView booklist;
    BookingAdapter bookadapt;
    String idmmb;
    Vector<String> nama = new Vector<>();
    Vector<String> alamat = new Vector<>();
    Vector<String> start = new Vector<>();
    Vector<String> end = new Vector<>();
    Vector<String> id = new Vector<>();
    Vector<Integer> total = new Vector<>();

    void init(){
        booklist = findViewById(R.id.listbooking);
        //filterMember();
    }

   public void filterMember(){
        Intent getIntent = getIntent();
        idmmb= getIntent.getStringExtra("idmember");
            for(int i = 0; i< DB.bookinglist.size();i++) {
                if (idmmb.equals(DB.bookinglist.get(i).mmbid)) {
                    int hotelid = Integer.parseInt(DB.bookinglist.get(i).htlid);
//                    Toast.makeText(this,""+DB.hotellist.get(hotelid-1).htlname,Toast.LENGTH_SHORT).show();
                    nama.add(DB.hotellist.get(hotelid-1).htlname);
                    alamat.add(DB.hotellist.get(hotelid-1).htlads);
                    start.add(DB.bookinglist.get(i).strdate);
                    end.add(DB.bookinglist.get(i).enddate);
                    total.add(DB.bookinglist.get(i).price);
                    id.add(DB.bookinglist.get(i).bkid);
                }
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        init();

        //bookadapt = new BookingAdapter(BookingPage.this,nama,alamat,start,end,id,total);
        bookadapt = new BookingAdapter(BookingPage.this);
        booklist.setAdapter(bookadapt);

    }
}
