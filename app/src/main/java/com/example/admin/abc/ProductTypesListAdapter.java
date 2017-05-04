package com.example.admin.abc;


/**
 * Created by Geetha on 4/12/2017 for displaying main product type images.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ProductTypesListAdapter extends BaseAdapter {

    Context c;

    ArrayList<ProductTypesDB> productTypesDBs;
    LayoutInflater inflater;
    String name;
    int pid;
    public ProductTypesListAdapter(Context c, ArrayList<ProductTypesDB> productTypesDBs,int pid, String name) {
        this.c = c;
        this.productTypesDBs = productTypesDBs;
        this.name = name;

        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return productTypesDBs.size();
    }
    @Override
    public Object getItem(int position) {
        return productTypesDBs.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.producttypeimage_list_view,parent,false);
        }
        TextView typeNameTxt= (TextView) convertView.findViewById(R.id.textViewURL1);
        ImageView img= (ImageView) convertView.findViewById(R.id.imageTypePro);
        //BIND DATA
        ProductTypesDB productTypesDB=(ProductTypesDB) this.getItem(position);
        final int ptid = productTypesDB.getProductTypeId();
        final String ptname = productTypesDB.getProductType();
        final int pid = productTypesDB.getProductId();
        typeNameTxt.setText(productTypesDB.getProductType());
        final String url = productTypesDB.getImageUrl();
        final String finalUrl=Config.mainUrlAddress + url;
        //IMG
        PicassoClient.downloadImage(c,finalUrl,img);

        // open new activity
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProductTypeSubTypesActivity(pid,name,ptid,ptname);
            }
        });

        return convertView;
    }

    public void openProductTypeSubTypesActivity(int pid,String name,int ptid,String ptname){
        Intent intent = new Intent(c,ProductSubTypes.class);
        intent.putExtra("PRODUCTID_KEY",pid);
        intent.putExtra("PRODUCTNAME_KEY",name);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        intent.putExtra("PRODUCTTYPENAME_KEY",ptname);
        c.startActivity(intent);
    }
}

