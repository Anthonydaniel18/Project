package com.example.assignmentmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.assignmentmad.ADAPTERS.HotelAdapter;
import com.example.assignmentmad.DB.DB;
import com.example.assignmentmad.DB.DBHELPER;
import com.example.assignmentmad.DB.MODELS.Booking;
import com.example.assignmentmad.DB.MODELS.Member;

public class HomePage extends AppCompatActivity {

    String mmbid,tel;
    ListView hotellist;
    HotelAdapter htladapt;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    void init(){
        hotellist = findViewById(R.id.listhotel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        openHelper = new DBHELPER(this);

        Intent getIntent = getIntent();
        mmbid = getIntent.getStringExtra("idmember");
        tel = getIntent.getStringExtra("notel");
        init();

        htladapt = new HotelAdapter(HomePage.this);

        hotellist.setAdapter(htladapt);

        hotellist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent enter2 = new Intent(HomePage.this, HotelDetail.class);

                String latitude = (DB.hotellist.get(position).latitude).toString();
                String longitude = (DB.hotellist.get(position).longitude).toString();
                String price = Integer.toString(DB.hotellist.get(position).price);

                enter2.putExtra("idhotel", DB.hotellist.get(position).htlid);
                enter2.putExtra("hotelname", DB.hotellist.get(position).htlname);
                enter2.putExtra("hotelads",DB.hotellist.get(position).htlads);
                enter2.putExtra("hotelphone", DB.hotellist.get(position).htlphn);
                enter2.putExtra("hotellat",DB.hotellist.get(position).latitude);
                enter2.putExtra("hotellong",DB.hotellist.get(position).longitude);
                enter2.putExtra("hotelprice",DB.hotellist.get(position).price);
                enter2.putExtra("hotelimage",DB.hotellist.get(position).image);
                enter2.putExtra("idmember", mmbid);
                enter2.putExtra("notel",tel);

                startActivity(enter2);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.hotelbooking:

                DB.bookinglist.clear();

                db = openHelper.getReadableDatabase();
                Cursor c = db.rawQuery("Select * From " + DBHELPER.tabel2,null);

                while(c.moveToNext()){
                    DB.bookinglist.add(new Booking(c.getString(0),c.getString(1),
                            c.getString(2),c.getString(3),c.getString(4),
                            c.getString(5),Integer.valueOf(c.getString(6))));

                    String a = c.getString(0);
                    String b = c.getString(1);
                    String f = c.getString(2);
                    String d = c.getString(3);
                    String e = c.getString(4);
                    String g = c.getString(5);
                    String h = c.getString(6);
                    Log.d("Database isi booking", String.format("%s,%s,%s,%s,%s,%s,%s",a,b,f,d,e,g,h));
                }

                Intent enter1 = new Intent(HomePage.this, BookingPage.class);
                enter1.putExtra("idmember", mmbid);
                startActivity(enter1);
                return true;

            case R.id.logout:
                Intent enter = new Intent(HomePage.this, LogIn.class);
                startActivity(enter);
                return true;

                default: return super.onOptionsItemSelected(item);
        }
    }

}
