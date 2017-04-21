package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Geetha on 4/20/2017 for storing database data into an array.
 */

public class ProductTypeSubTypesDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    String jsonData;
    int ptid;
    int pid;
    ArrayList<ProductTypeSubTypeItem> productTypeSubTypeItems=new ArrayList<>();

    public ProductTypeSubTypesDataParser(Context c, ListView lv, String jsonData, int ptid, int pid) {
        this.c = c;
        this.lv = lv;
        this.jsonData = jsonData;
        this.ptid = ptid;
        this.pid = pid;
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
            //Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
            // opening new activity
            openProductSizesActivity(pid,ptid);
        }else
        {

            final ProductTypeSubTypesListAdapter adapter=new ProductTypeSubTypesListAdapter(c,productTypeSubTypeItems);
            lv.setAdapter(adapter);

        }
    }

    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            productTypeSubTypeItems.clear();
            ProductTypeSubTypeItem productTypeSubTypeItem;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);
                int ProductSubTypeId=jo.getInt("ProductSubTypeId");
                String ProductSubTypeName =jo.getString("ProductSubTypeName");
                String ImageUrl=jo.getString("ImageUrl");
                int ProductTypeId=jo.getInt("ProductTypeId");
                productTypeSubTypeItem=new ProductTypeSubTypeItem();
                productTypeSubTypeItem.setProductSubTypeId(ProductSubTypeId);
                productTypeSubTypeItem.setProductSubTypeName(ProductSubTypeName);
                productTypeSubTypeItem.setImageUrl(ImageUrl);
                productTypeSubTypeItem.setProductTypeId(ProductTypeId);
                productTypeSubTypeItems.add(productTypeSubTypeItem);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void openProductSizesActivity(int pid,int ptid) {
        Intent intent = new Intent(c,ProductTypeSizes.class);
        intent.putExtra("PRODUCTID_KEY",pid);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        c.startActivity(intent);
    }
}
