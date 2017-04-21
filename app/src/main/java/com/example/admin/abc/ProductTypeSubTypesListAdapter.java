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
 * Created by Geetha on 4/20/2017 for displaying products sub types in listview.
 */

public class ProductTypeSubTypesListAdapter extends BaseAdapter {

    Context c;

    ArrayList<ProductTypeSubTypeItem> productTypeSubTypeItems;
    LayoutInflater inflater;
    public ProductTypeSubTypesListAdapter(Context c, ArrayList<ProductTypeSubTypeItem> productTypeSubTypeItems) {
        this.c = c;
        this.productTypeSubTypeItems = productTypeSubTypeItems;

        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return productTypeSubTypeItems.size();
    }
    @Override
    public Object getItem(int position) {
        return productTypeSubTypeItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.producttypesubtypeimage_list_view,parent,false);
        }
        TextView typeNameTxt= (TextView) convertView.findViewById(R.id.txtSubTypePro);
        ImageView img= (ImageView) convertView.findViewById(R.id.imageSubTypePro);
        //BIND DATA
        ProductTypeSubTypeItem  productTypeSubTypeItem=(ProductTypeSubTypeItem) this.getItem(position);
        final int pstid = productTypeSubTypeItem.getProductSubTypeId();
        final int ptid = productTypeSubTypeItem.getProductTypeId();
        final String pstname = productTypeSubTypeItem.getProductSubTypeName();
        typeNameTxt.setText(productTypeSubTypeItem.getProductSubTypeName());
        //IMG
        PicassoClient.downloadImage(c,productTypeSubTypeItem.getImageUrl(),img);

        // open new activity
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProductTypeSizeImagessActivity(ptid,pstid);
            }
        });

        return convertView;
    }

    public void openProductTypeSizeImagessActivity(int ptid, int pstid){
        Intent intent = new Intent(c,ProductTypeSubTypeImages.class);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        intent.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
        c.startActivity(intent);
    }
}

