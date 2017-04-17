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


public class TypeImageGridAdapterClass extends BaseAdapter {

    Context c;
    ArrayList<ProductTypeItem> productTypeItems;
    LayoutInflater inflater;
    public TypeImageGridAdapterClass(Context c, ArrayList<ProductTypeItem> productTypeItems) {
        this.c = c;
        this.productTypeItems = productTypeItems;
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
            convertView=inflater.inflate(R.layout.producttypeimage_grid_view,parent,false);
        }
        TextView typeNameTxt= (TextView) convertView.findViewById(R.id.textViewURL1);
        ImageView img= (ImageView) convertView.findViewById(R.id.imageTypePro);
        //BIND DATA
        ProductTypeItem productTypeItem=(ProductTypeItem) this.getItem(position);
      //  final String name = productImage.getName();
      //  final String url = productImage.getImageUrl();
        final int pid = productTypeItem.getProductTypeId();
        typeNameTxt.setText(productTypeItem.getProductType());
        //IMG
        PicassoClient.downloadImage(c,productTypeItem.getImageUrl(),img);

        // open new activity

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProductTypeSizesActivity(pid);
            }
        });

        return convertView;
    }

    public void openProductTypeSizesActivity(int pid){
        Intent intent = new Intent(c,BasinsSize.class);
       // intent.putExtra("PRODUCTID_KEY", pid);
        c.startActivity(intent);
    }

}

