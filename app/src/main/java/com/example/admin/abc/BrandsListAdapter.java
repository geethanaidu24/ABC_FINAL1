package com.example.admin.abc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Admin on 4/18/2017.
 */

public class BrandsListAdapter extends BaseAdapter {

    Context c;
    ArrayList<BrandsImages> brandsImages;
    LayoutInflater inflater;
    public BrandsListAdapter(Context c, ArrayList<BrandsImages> brandsImages) {
        this.c = c;
        this.brandsImages = brandsImages;
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return brandsImages.size();
    }
    @Override
    public Object getItem(int position) {
        return brandsImages.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.brands_list_gridview, parent,false);
        }

        ImageView img= (ImageView) convertView.findViewById(R.id.brandgridimg);
        //BIND DATA
        BrandsImages brandsImage=(BrandsImages) this.getItem(position);

        //IMG
        PicassoClient.downloadImage(c,brandsImage.getImagePath(),img);

        return convertView;
    }

}
