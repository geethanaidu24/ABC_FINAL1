package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Geetha on 4/21/2017.
 */

class ProductTypeImagesGirdAdapter extends BaseAdapter {
    Context c;

    ArrayList<ProductTypeSubTypeImageItem> productTypeSubTypeImageItems;
    LayoutInflater inflater;
    int pid,ptid;
    String pname;
    public ProductTypeImagesGirdAdapter(Context c, ArrayList<ProductTypeSubTypeImageItem> productTypeImageItems, int pid,int ptid,String pname) {
        this.c = c;
        this.productTypeSubTypeImageItems = productTypeImageItems;
        this.pid=pid;
        this.ptid=ptid;
        this.pname=pname;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productTypeSubTypeImageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return productTypeSubTypeImageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_layout, parent, false);
        }
        TextView typeNameTxt = (TextView) convertView.findViewById(R.id.txtTypeSizePro);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgTypeSizePro);
        //BIND DATA
        ProductTypeSubTypeImageItem productTypeSubTypeImageItem = (ProductTypeSubTypeImageItem) this.getItem(position);

        final int imageid = productTypeSubTypeImageItem.getProductSizeImageId();
        typeNameTxt.setText(productTypeSubTypeImageItem.getName());

        final String url = productTypeSubTypeImageItem.getImagePath();
        try {
            java.net.URLEncoder.encode(url,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //IMG
        PicassoClient.downloadImage(c, url, img);
        //BIND DATA
        final String name = productTypeSubTypeImageItem.getName();

        final String brand = productTypeSubTypeImageItem.getBrand();
        final String color = productTypeSubTypeImageItem.getColor();
        final int protypeid = productTypeSubTypeImageItem.getProductTypeId();
        final int proid = productTypeSubTypeImageItem.getProductId();
        // open new activity
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //open detail activity
                // startDeatilActivity();
                openDetailActivity(pid,ptid,pname,name,url,brand,color);
            }
        });
        return convertView;
    }
    private void openDetailActivity(int pid, int ptid,String pname, String...details)
    {
        Intent i = new Intent(c,ProductTypeSingleViewActivity.class);
        i.putExtra("PRODUCTID_KEY", pid);
        i.putExtra("PRODUCTTYPEID_KEY", ptid);
        i.putExtra("PRODUCTNAME_KEY",pname);
        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("IMAGE_KEY",details[1]);
        i.putExtra("BRAND_KEY", details[2]);
        i.putExtra("COLOR_KEY", details[3]);
        c.startActivity(i);
    }
}
