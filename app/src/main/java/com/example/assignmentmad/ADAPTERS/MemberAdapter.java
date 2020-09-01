package com.example.assignmentmad.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.assignmentmad.DB.MODELS.Member;
import com.example.assignmentmad.R;

import java.util.Vector;

public class MemberAdapter extends BaseAdapter {

    Context ctx;
    Vector<Member> mem;

    public MemberAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return mem.size();
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
//
//        LayoutInflater inflater = LayoutInflater.from(ctx);
//        View v = inflater.inflate(R.layout.activity_log_in, null);

        Member m = mem.get(position);

        return null;
    }
}
