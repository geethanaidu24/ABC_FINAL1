package com.example.admin.abc;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 4/18/2017.
 */

class BrandsDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;

    GridView gridView1;
    String jsonData;

    ArrayList<ProductImages> productImages=new ArrayList<>();
    public BrandsDataParser(Context c,GridView gridView1, String jsonData) {
        this.c = c;
        this.gridView1 = gridView1;
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

            final ProductsListAdapter adapter=new ProductsListAdapter(c,productImages);
            gridView1.setAdapter(adapter);
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

                productImage.setImageUrl(ImageUrl);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
