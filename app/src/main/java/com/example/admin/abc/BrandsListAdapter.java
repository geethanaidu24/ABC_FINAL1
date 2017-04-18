package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 4/18/2017.
 */

public class BrandsListAdapter extends BaseAdapter {

    Context c;
    ArrayList<BrandsImages> productImages;
    LayoutInflater inflater;
    public BrandsListAdapter(Context c, ArrayList<BrandsImages> productImages) {
        this.c = c;
        this.productImages = productImages;
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return productImages.size();
    }
    @Override
    public Object getItem(int position) {
        return productImages.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.productimage_list_view, parent,false);
        }

        ImageView img= (ImageView) convertView.findViewById(R.id.imageDownloaded);
        //BIND DATA
        ProductImages productImage=(ProductImages) this.getItem(position);
        final String name = productImage.getName();
        final String url = productImage.getImageUrl();
        final int pid = productImage.getId();

        //IMG
        PicassoClient.downloadImage(c,productImage.getImageUrl(),img);

        return convertView;
    }

}
