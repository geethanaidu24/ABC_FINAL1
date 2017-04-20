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
 * Created by Geetha on 4/18/2017.
 */

class ProductTypeSizeImagesDataParser  extends AsyncTask<Void,Void,Integer> {
    Context c;
    GridView gv;
    String jsonData;
    ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems=new ArrayList<>();

    public ProductTypeSizeImagesDataParser(Context c, GridView gv, String jsonData) {
        this.c = c;
        this.gv = gv;
        this.jsonData = jsonData;

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

            final ProductTypeSizeImagesGirdAdapter adapter=new ProductTypeSizeImagesGirdAdapter(c,productTypeSizeImageItems);
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

                int ImageId=jo.getInt("ImageId");
                String Name =jo.getString("Name");
                String ImageUrl=jo.getString("ImagePath");
                String Brands = jo.getString("Brands");
                String Color = jo.getString("Color");
                int SizeId = jo.getInt("SizeId");
                productTypeSizeImageItem=new ProductTypeSizeImageItem();
                productTypeSizeImageItem.setImageId(ImageId);
                productTypeSizeImageItem.setName(Name);
                productTypeSizeImageItem.setImagePath(ImageUrl);
                productTypeSizeImageItem.setBrands(Brands);
                productTypeSizeImageItem.setColor(Color);
                productTypeSizeImageItem.setSizeId(SizeId);
                productTypeSizeImageItems.add(productTypeSizeImageItem);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
