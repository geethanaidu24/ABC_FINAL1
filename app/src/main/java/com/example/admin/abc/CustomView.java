package com.example.admin.abc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Admin on 4/13/2017.
 */
public class CustomView extends BaseAdapter {
    String s[];
    Integer pics[];
    Context cn;

    public CustomView(Products products, String[] bathroomtypes, Integer[] typesimages) {
        this.s=bathroomtypes;
        this.pics=typesimages;
        this.cn=products;



    }

    @Override
    public int getCount() {
        return s.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)cn.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.list_single,null);
        TextView tx=(TextView)convertView.findViewById(R.id.textView);
        ImageView im=(ImageView)convertView.findViewById(R.id.imageView);
        tx.setText(s[position]);
        im.setImageResource(pics[position]);


        return convertView;
    }
}
