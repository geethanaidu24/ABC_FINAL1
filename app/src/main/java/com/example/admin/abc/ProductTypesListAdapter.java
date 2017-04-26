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

    ArrayList<ProductTypeItem> productTypeItems;
    LayoutInflater inflater;
    String name;
    int pid;
    public ProductTypesListAdapter(Context c, ArrayList<ProductTypeItem> productTypeItems,int pid, String name) {
        this.c = c;
        this.productTypeItems = productTypeItems;
        this.name = name;

        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return productTypeItems.size();
    }
    @Override
    public Object getItem(int position) {
        return productTypeItems.get(position);
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
        ProductTypeItem productTypeItem=(ProductTypeItem) this.getItem(position);
        final int ptid = productTypeItem.getProductTypeId();
        final String ptname = productTypeItem.getProductType();
        final int pid = productTypeItem.getProductId();
        typeNameTxt.setText(productTypeItem.getProductType());
        final String url = productTypeItem.getImageUrl();
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
        Intent intent = new Intent(c,ProductTypeSubTypes.class);
        intent.putExtra("PRODUCTID_KEY",pid);
        intent.putExtra("PRODUCTNAME_KEY",name);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        intent.putExtra("PRODUCTTYPENAME_KEY",ptname);
        c.startActivity(intent);
    }
}

