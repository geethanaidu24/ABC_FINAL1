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
 * Created by Geetha on 4/18/2017 for displaying main product type images sizes based on size display images.
 */

public class ProductTypeSizeImagesGirdAdapter extends BaseAdapter {
    Context c;

    ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems;
    LayoutInflater inflater;
   static String finalSize ;
    int pid,ptid,ptsid;
    String pname;
    public ProductTypeSizeImagesGirdAdapter(Context c, ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems, int pid, String pname, int ptid, int ptsid) {
        this.c = c;
        this.productTypeSizeImageItems = productTypeSizeImageItems;
this.pid= pid;
        this.pname=pname;
        this.ptid=ptid;
        this.ptsid=ptsid;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productTypeSizeImageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return productTypeSizeImageItems.get(position);
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
        ProductTypeSizeImageItem productTypeSizeImageItem = (ProductTypeSizeImageItem) this.getItem(position);

        typeNameTxt.setText(productTypeSizeImageItem.getName());

        //IMG
        PicassoClient.downloadImage(c, productTypeSizeImageItem.getImagePath(), img);
        //BIND DATA
        final String name = productTypeSizeImageItem.getName();
        final String url = productTypeSizeImageItem.getImagePath();
        final String brand = productTypeSizeImageItem.getBrand();
        final String color = productTypeSizeImageItem.getColor();
        final int sizeid = productTypeSizeImageItem.getProductSizeId();
        final int width = Integer.parseInt(String.valueOf(productTypeSizeImageItem.getWidth()).toString());
        final int height = Integer.parseInt(String.valueOf(productTypeSizeImageItem.getHeight()).toString());
        final int length = Integer.parseInt(String.valueOf(productTypeSizeImageItem.getLength()).toString());




        if(length !=0 && width !=0 && height !=0){
            finalSize =  width + "X" + height + "X" + length;

        }else if(length ==0 && width !=0 && height !=0){
            finalSize =  width + "X" + height;

        }else if(length !=0 && width ==0 && height !=0){
            finalSize =  length + "X" + height;

        }else if(length !=0 && width !=0 && height ==0 ){
            finalSize =  length + "X" + width ;

        }else if(length ==0 && width !=0 && height ==0 ){
            finalSize = width + "" ;

        }else if(length !=0 && width ==0 && height ==0 ){
            finalSize = length + "" ;

        }else if(length ==0 && width ==0 && height !=0 ){
            finalSize = height + "" ;

        }

        // open new activity
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //open detail activity
                openDetailActivity(pid,pname,ptid,ptsid,name,url,brand,color,finalSize);
            }
        });
        return convertView;
    }
    private void openDetailActivity(int pid,String pname,int ptid,int ptsid,String...details)
    {
        Intent i = new Intent(c,SingleViewActivity.class);
        i.putExtra("PRODUCTID_KEY",pid);
        i.putExtra("PRODUCTNAME_KEY",pname);
        i.putExtra("PRODUCTTYPEID_KEY",ptid);
        i.putExtra("PRODUCTTYPESIZEID_KEY",ptsid);
        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("IMAGE_KEY",details[1]);
        i.putExtra("BRAND_KEY", details[2]);
        i.putExtra("COLOR_KEY", details[3]);
        i.putExtra("SIZE_KEY", details[4]);
        c.startActivity(i);
    }
}