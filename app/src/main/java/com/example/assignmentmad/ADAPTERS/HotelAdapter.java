package com.example.assignmentmad.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignmentmad.DB.DB;
import com.example.assignmentmad.DB.MODELS.Hotel;
import com.example.assignmentmad.R;
import com.squareup.picasso.Picasso;

import java.util.Vector;

public class HotelAdapter extends BaseAdapter {

    Context ctx;

    public HotelAdapter(Context ctx){
        this.ctx = ctx;
    }


    @Override
    public int getCount() {
        return DB.hotellist.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(ctx);

        View v = inflater.inflate(R.layout.list_hotel,null);

        Hotel h = DB.hotellist.get(position);

        TextView name = v.findViewById(R.id.hotelname);
        TextView ads = v.findViewById(R.id.hotelads);
        TextView price = v.findViewById(R.id.hotelprice1);
        ImageView image = v.findViewById(R.id.gambarHotel);

        name.setText(h.htlname);
        ads.setText(h.htlads);
        price.setText(Integer.toString(h.price));
        Picasso.get().load(h.getImage()).into(image);

        return v;

    }
}
