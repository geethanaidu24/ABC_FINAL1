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
 * Created by Atwyn on 4/21/2017.
 */

class ProductSizeImagesDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    GridView gv;
    String jsonData;
    int pid,psid;

    ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems=new ArrayList<>();

    public ProductSizeImagesDataParser(Context c, GridView gv, String jsonData,int pid,int psid) {
        this.c = c;
        this.gv = gv;
        this.jsonData = jsonData;
        this.pid = pid;
        this.psid = psid;


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

            final ProductSizeImagesGirdAdapter adapter=new ProductSizeImagesGirdAdapter(c,productTypeSizeImageItems,pid,psid);
            gv.setAdapter(adapter);

        }
    }
    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            productTypeSizeImageItems.clear();
            ProductTypeSizeImageItem productTypeSizeImageItem;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);

                int ImageId=jo.getInt("ProductSizeImageId");
                String Name =jo.getString("Name");
                String ImageUrl=jo.getString("ImagePath");
                String Brands = jo.getString("Brand");
                String Color = jo.getString("Color");
                int SizeId = jo.getInt("ProductSizeId");
                int ProductSTypeId = jo.optInt("ProductSubTypeId", 0);
                int ProductTypeId = jo.optInt("ProductTypeId",0);
                int ProductId = jo.getInt("ProductId");
                int SizeWidth = jo.getInt("Width");
                int SizeHeight = jo.getInt("Height");
                int SizeLength = jo.getInt("Length");
                productTypeSizeImageItem=new ProductTypeSizeImageItem();
                productTypeSizeImageItem.setProductSizeImageId(ImageId);
                productTypeSizeImageItem.setName(Name);
                productTypeSizeImageItem.setImagePath(ImageUrl);
                productTypeSizeImageItem.setBrand(Brands);
                productTypeSizeImageItem.setColor(Color);
                productTypeSizeImageItem.setProductSizeId(SizeId);
                productTypeSizeImageItem.setProductSubTypeId(ProductSTypeId);
                productTypeSizeImageItem.setProductTypeId(ProductTypeId);
                productTypeSizeImageItem.setProductId(ProductId);
                productTypeSizeImageItem.setWidth(SizeWidth);
                productTypeSizeImageItem.setHeight(SizeHeight);
                productTypeSizeImageItem.setLength(SizeLength);

                productTypeSizeImageItems.add(productTypeSizeImageItem);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
