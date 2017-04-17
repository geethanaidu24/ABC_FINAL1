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
 * Created by Geetha on 4/8/2017.
 */

class BasinImageListAdapterClass extends BaseAdapter

    {

        Context c;
        ArrayList<BasinImages> basinImages;
        LayoutInflater inflater;
    public BasinImageListAdapterClass(Context c, ArrayList<BasinImages> basinImages) {
        this.c = c;
        this.basinImages = basinImages;
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
        @Override
        public int getCount() {
        return basinImages.size();
    }
        @Override
        public Object getItem(int position) {
        return basinImages.get(position);
    }
        @Override
        public long getItemId(int position) {
        return position;
    }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.gridview_layout,parent,false);
        }
        TextView nametxt= (TextView) convertView.findViewById(R.id.cabecera);
        ImageView img= (ImageView) convertView.findViewById(R.id.imagen);
        //BIND DATA
        BasinImages basinImage=(BasinImages) this.getItem(position);

            final String name = basinImage.getName();
            final String url = basinImage.getUrl();
            final int id = basinImage.getId();
            final String brand = basinImage.getBrands();
            final String color = basinImage.getColor();
            final int sizeid = basinImage.getSizeid();
        nametxt.setText(basinImage.getName());
        //IMG
        PicassoClient.downloadImage(c,basinImage.getUrl(),img);
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //open detail activity
                   // startDeatilActivity();
                    openDetailActivity(name,url,brand,color);
                }
            });
        return convertView;
    }
    private void openDetailActivity(String...details)
    {
        Intent i = new Intent(c,SingleViewActivity.class);
        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("IMAGE_KEY",details[1]);
        i.putExtra("BRAND_KEY", details[2]);
        i.putExtra("COLOR_KEY", details[3]);

        c.startActivity(i);
    }
}
