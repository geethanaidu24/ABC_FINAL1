package com.example.admin.abc;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Geetha on 4/20/2017.
 */

class ProductTypeSubTypeImagesDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    GridView gv;
    String jsonData;
    int pid,ptid,pstid;
    String pname, ptname;
    ArrayList<ProductTypeSubTypeImageItem> productTypeSubTypeImageItems=new ArrayList<>();

    public ProductTypeSubTypeImagesDataParser(Context c, GridView gv, String jsonData,int pid,String pname, int ptid, String ptname, int pstid) {
        this.c = c;
        this.gv = gv;
        this.jsonData = jsonData;
        this.pid = pid;
        this.pname = pname;
        this.ptid = ptid;
        this.ptname = ptname;
        this.pstid = pstid;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }
    @Override

    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        if(result==0)
        {
            Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
        }else
        {

            final ProductTypeSubTypeImagesGirdAdapter adapter=new ProductTypeSubTypeImagesGirdAdapter(c,productTypeSubTypeImageItems,pid,pname,ptid,ptname,pstid);
            gv.setAdapter(adapter);

        }
    }
    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            productTypeSubTypeImageItems.clear();
            ProductTypeSubTypeImageItem productTypeSubTypeImageItem;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);

                int ProductSizeImageId = jo.getInt("ProductSizeImageId");
                String Name =jo.getString("Name");
                String ImageUrl=jo.getString("ImagePath");
                String Brands = jo.getString("Brand");
                String Color = jo.getString("Color");
                int ProductSizeId = jo.optInt("ProductSizeId");
                int ProductSubTypeId = jo.getInt("ProductSubTypeId");
                int ProductTypeId = jo.optInt("ProductTypeId");
                int ProductId = jo.getInt("ProductId");
                productTypeSubTypeImageItem=new ProductTypeSubTypeImageItem();
                productTypeSubTypeImageItem.setProductSizeId(ProductSizeImageId);
                productTypeSubTypeImageItem.setName(Name);
                productTypeSubTypeImageItem.setImagePath(ImageUrl);
                productTypeSubTypeImageItem.setBrand(Brands);
                productTypeSubTypeImageItem.setColor(Color);
                productTypeSubTypeImageItem.setProductSizeId(ProductSizeId);
                productTypeSubTypeImageItem.setProductSubTypeId(ProductSubTypeId);
                productTypeSubTypeImageItem.setProductTypeId(ProductTypeId);
                productTypeSubTypeImageItem.setProductId(ProductId);
                productTypeSubTypeImageItems.add(productTypeSubTypeImageItem);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
