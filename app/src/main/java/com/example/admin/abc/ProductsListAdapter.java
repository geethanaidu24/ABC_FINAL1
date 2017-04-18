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


public class ProductsListAdapter extends BaseAdapter {

    Context c;
    ArrayList<ProductImages> productImages;
    LayoutInflater inflater;
    public ProductsListAdapter(Context c, ArrayList<ProductImages> productImages) {
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
        TextView nametxt= (TextView) convertView.findViewById(R.id.textViewURL);
        ImageView img= (ImageView) convertView.findViewById(R.id.imageDownloaded);
        //BIND DATA
        ProductImages productImage=(ProductImages) this.getItem(position);
        final String name = productImage.getName();
        final String url = productImage.getImageUrl();
        final int pid = productImage.getId();
        nametxt.setText(productImage.getName());
        //IMG
        PicassoClient.downloadImage(c,productImage.getImageUrl(),img);
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProductTypesActivity(pid, name);
            }
        });
        return convertView;
    }
    public void openProductTypesActivity(int pid, String name){
        Intent intent = new Intent(c,ProductTypes.class);
        intent.putExtra("PRODUCTID_KEY", pid);
        intent.putExtra("PRODUCTNAME_KEY",name);
        c.startActivity(intent);
    }
}
