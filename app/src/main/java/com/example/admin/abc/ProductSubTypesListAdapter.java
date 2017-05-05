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

public class ProductSubTypesListAdapter extends BaseAdapter {

    Context c;

    ArrayList<ProductSubTypesDB> productSubTypesDBs;
    int pid,ptid;
    String pname,ptname;
    LayoutInflater inflater;
    public ProductSubTypesListAdapter(Context c, ArrayList<ProductSubTypesDB> productSubTypesDBs, int pid, String pname, int ptid, String ptname) {
        this.c = c;
        this.productSubTypesDBs = productSubTypesDBs;
        this.pid = pid;
        this.pname = pname;
        this.ptid = ptid;
        this.ptname = ptname;
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return productSubTypesDBs.size();
    }
    @Override
    public Object getItem(int position) {
        return productSubTypesDBs.get(position);
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
        ProductSubTypesDB  productSubTypesDB=(ProductSubTypesDB) this.getItem(position);
        final int pstid = productSubTypesDB.getProductSubTypeId();
        final int ptid = productSubTypesDB.getProductTypeId();
        final String pstname = productSubTypesDB.getProductSubTypeName();
        final String url = productSubTypesDB.getImageUrl();
        final String finalUrl=Config.mainUrlAddress + url;
        typeNameTxt.setText(productSubTypesDB.getProductSubTypeName());
        //IMG
        PicassoClient.downloadImage(c,finalUrl,img);

        // open new activity
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProductTypeSizeImagessActivity(pid,pname,ptid,ptname,pstid);
            }
        });

        return convertView;
    }

    public void openProductTypeSizeImagessActivity(int pid, String pname, int ptid, String ptname,  int pstid){
        Intent intent = new Intent(c,ProductSubTypeGridView.class);
        intent.putExtra("PRODUCTID_KEY",pid);
        intent.putExtra("PRODUCTNAME_KEY",pname);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        intent.putExtra("PRODUCTTYPENAME_KEY",ptname);
        intent.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
        c.startActivity(intent);
    }
}

