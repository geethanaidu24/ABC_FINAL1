package com.example.admin.abc;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Geetha on 4/12/2017 for Parsing previously collected data and storing into ProductImages class .
 */

public class ProductsDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    String jsonData;

    ArrayList<ProductsDB> productsDBs=new ArrayList<>();
    public ProductsDataParser(Context c, ListView lv, String jsonData) {
        this.c = c;
        this.lv = lv;
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

            final ProductsListAdapter adapter=new ProductsListAdapter(c,productsDBs);
            lv.setAdapter(adapter);
        }
    }

    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            productsDBs.clear();
            ProductsDB productsDB;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);
                int ProductId=jo.getInt("ProductId");
                String ProductName =jo.getString("ProductName");
                String ImageUrl=jo.getString("ImageUrl");
                productsDB=new ProductsDB();
                productsDB.setId(ProductId);
                productsDB.setName(ProductName);
                productsDB.setImageUrl(ImageUrl);
                productsDBs.add(productsDB);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
