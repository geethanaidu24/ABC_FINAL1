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
 * Created by Geetha on 4/20/2017.
 */

public class ProductTypeSubTypeImagesGirdAdapter extends BaseAdapter {
    Context c;

    ArrayList<ProductTypeSubTypeImageItem> productTypeSubTypeImageItems;
    int pid,ptid,pstid;
    String pname,ptname;
    LayoutInflater inflater;

    public ProductTypeSubTypeImagesGirdAdapter(Context c, ArrayList<ProductTypeSubTypeImageItem> productTypeSubTypeImageItems, int pid, String pname, int ptid, String ptname,int pstid) {
        this.c = c;
        this.productTypeSubTypeImageItems = productTypeSubTypeImageItems;
        this.pid = pid;
        this.pname = pname;
        this.ptid = ptid;
        this.ptname = ptname;
        this.pstid = pstid;
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
        final String finalUrl=Config.mainUrlAddress + url;
        try {
            java.net.URLEncoder.encode(url,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //IMG
        PicassoClient.downloadImage(c, finalUrl, img);
        //BIND DATA
        final String name = productTypeSubTypeImageItem.getName();

        final String brand = productTypeSubTypeImageItem.getBrand();
        final String color = productTypeSubTypeImageItem.getColor();
        final int protypeid = productTypeSubTypeImageItem.getProductTypeId();
        final int prosubtypeid = productTypeSubTypeImageItem.getProductSubTypeId();
               // open new activity
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //open detail activity
                // startDeatilActivity();
                openDetailActivity(pid,pname,ptid,ptname,pstid,name,finalUrl,brand,color);
            }
        });
        return convertView;
    }
    private void openDetailActivity(int pid, String pname,int ptid,String ptname, int pstid, String...details)
    {
        Intent i = new Intent(c,ProductSubTypeSingleViewActivity.class);
        i.putExtra("PRODUCTID_KEY",pid);
        i.putExtra("PRODUCTNAME_KEY",pname);
        i.putExtra("PRODUCTTYPEID_KEY", ptid);
        i.putExtra("PRODUCTTYPENAME_KEY",ptname);
        i.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("IMAGE_KEY",details[1]);
        i.putExtra("BRAND_KEY", details[2]);
        i.putExtra("COLOR_KEY", details[3]);
        c.startActivity(i);
    }
}
