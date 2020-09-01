package com.example.assignmentmad.ADAPTERS;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignmentmad.BookingPage;
import com.example.assignmentmad.DB.DB;
import com.example.assignmentmad.DB.DBHELPER;
import com.example.assignmentmad.DB.MODELS.Booking;
import com.example.assignmentmad.DB.MODELS.Hotel;
import com.example.assignmentmad.HomePage;
import com.example.assignmentmad.R;

import java.util.Vector;

public class BookingAdapter extends BaseAdapter {

    Context ctx;
    Vector<Booking> book = DB.bookinglist;
    Vector<Hotel> hotel = DB.hotellist;
    LayoutInflater inflater;
    String idhotel;
    Vector<String> nama,alamat,start,end,id;
    Vector<Integer> total;

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

        //public BookingAdapter(Context ctx,  Vector<String> nama,Vector<String>alamat,Vector<String>start, Vector<String>end,Vector<String>id,Vector<Integer> total){
        public BookingAdapter(Context ctx){
        this.ctx = ctx;
        //Vector<Booking> bookings = new Vector<Booking>();
        //this.book.addAll(bookings);
        //this.nama = nama;
        //this.alamat = alamat;
        //this.start = start;
        //this.end = end;
        //this.id = id;
        //this.total = total;
    }

    @Override
    public int getCount() {
        return DB.bookinglist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView  = inflater.inflate(R.layout.list_booking,null);

        Booking b = DB.bookinglist.get(position);

        TextView name = convertView.findViewById(R.id.htlname);
        //TextView ads = convertView.findViewById(R.id.htlads);
        TextView cekin= convertView.findViewById(R.id.indate);
        TextView cekout= convertView.findViewById(R.id.outdate);
        TextView price = convertView.findViewById(R.id.totprice);
        Button delete = convertView.findViewById(R.id.delbtn);

        final int ids = Integer.valueOf(b.bkid);

        name.setText(b.htlname);
        //ads.setText(alamat.get(position));
        cekin.setText(b.strdate);
        cekout.setText(b.enddate);
        price.setText(Integer.toString(b.price) );

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Do you want to delete this booking?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //for(int i = 0 ; i < DB.bookinglist.size() ; i++){
                          //  if(DB.bookinglist.get(position).equals(DB.bookinglist.get(i).bkid)){

                                DB.bookinglist.remove(position);

                            //}
                        //}

                        openHelper = new DBHELPER(ctx);
                        db = openHelper.getWritableDatabase();
                        String sql = "DELETE FROM " + DBHELPER.tabel2 + " WHERE id = " + ids;
                        db.execSQL(sql);

                        //DB.bookinglist.remove(position);
                        //nama.remove(position);
                        //alamat.remove(position);
                        //start.remove(position);
                        //end.remove(position);
                        //id.remove(position);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No",null);
                AlertDialog dialog = builder.create();
                dialog.show();;

                notifyDataSetChanged();

            }
        });

        return convertView;

    }
}
