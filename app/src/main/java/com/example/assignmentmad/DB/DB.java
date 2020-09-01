package com.example.assignmentmad.DB;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignmentmad.DB.MODELS.Booking;
import com.example.assignmentmad.DB.MODELS.Hotel;
import com.example.assignmentmad.DB.MODELS.Member;
import com.example.assignmentmad.Register;

import java.util.Vector;

public class DB {

    public static Vector <Member> memberlist = new Vector<>();
    public static Vector <Hotel> hotellist = new Vector<>();
    public static Vector <Booking> bookinglist = new Vector<>();


    public static void generateDataBooking(){
        if(bookinglist.isEmpty()){
            Booking b = new Booking("1","1","3","Veranda residence","23/11//2019","24/11/2019",3509000);
            bookinglist.add(b);

             b = new Booking("2","1","2","Ruru Urban Uma Dewata","24/12/2019","28/12/2019",12105260);
            bookinglist.add(b);

             b = new Booking("3","2","1","Couleur","24/12/2019","25/12/2019",2896341);
            bookinglist.add(b);
        }
    }

    public  static void generateData(){
        if (memberlist.isEmpty()){
            Member m = new Member(1,"New Admin","admin@gmail.com","Pika123","087880373678");
            memberlist.add(m);

            m = new Member(2,"Lala Luna","lala@gmail.com","Luna345","081362112321");
            memberlist.add(m);

        }

    }

    public static void generateDataHotel(){

        if (hotellist.isEmpty()){
           // Hotel h = new Hotel("1","NEO+","Kebayoran","02122777888","-6.2377162","-6.2377162",364000);
           // hotellist.add(h);

          //  h = new Hotel("2","Horison", "Ciledug","02130487700","-6.2364611","106.744531",500000);
          //  hotellist.add(h);

          //  h = new Hotel("3","Grand Setiabudi","Jakarta","02220440022","-6.8748216","107.5949251", 650000);
           // hotellist.add(h);
        }
    }

    public static void addBooking(String bkid, String mmbid, String htlid, String htlname, String strdate, String enddate, int price){
        bookinglist.add(new Booking(bkid, mmbid, htlid, htlname, strdate, enddate, price));
    }


    public static void addMember(int mmbid, String mmbname, String mmbemail, String mmbpass, String mmbtel){
        memberlist.add(new Member(mmbid,mmbname,mmbemail,mmbpass,mmbtel));
    }

}
