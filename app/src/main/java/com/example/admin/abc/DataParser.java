package com.example.admin.abc;

/**
 * Created by Geetha on 4/8/2017 for parses our downloaded json data natively.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    GridView gv;
    String jsonData;

    ArrayList<ProductImages> productImages=new ArrayList<>();
    public DataParser(Context c, GridView gv, String jsonData) {
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
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }else {
            //BIND DATA TO LISTVIEW
            ImageListAdapterClass adapter=new ImageListAdapterClass(c,productImages);
            gv.setAdapter(adapter);
        }
    }
    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            productImages.clear();
            ProductImages productImage;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);
                int ProductId=jo.getInt("ProductId");
                String ProductName =jo.getString("ProductName");
                String ImageUrl=jo.getString("ImageUrl");
                productImage=new ProductImages();
                productImage.setId(ProductId);
                productImage.setName(ProductName);
                productImage.setImageUrl(ImageUrl);
                productImages.add(productImage);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}