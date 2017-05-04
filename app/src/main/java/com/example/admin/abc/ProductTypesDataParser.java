package com.example.admin.abc;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Geetha on 4/12/2017 for parsing database data and stored into ProductTypeItem array or if not their goes to another related activity.
 */

public class ProductTypesDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    LinearLayout ll;
    String jsonData;
    int pid;
    String name;
    ArrayList<ProductTypesDB> productTypesDBs=new ArrayList<>();

    public ProductTypesDataParser(Context c, ListView lv, LinearLayout ll,String jsonData, int pid, String name) {
        this.c = c;
        this.lv = lv;
        this.ll=ll;
        this.jsonData = jsonData;
        this.pid = pid;
        this.name = name;

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
            openProductSizesActivity(pid);
        }else
        {
            ll.setVisibility(View.VISIBLE);

            final ProductTypesListAdapter adapter=new ProductTypesListAdapter(c,productTypesDBs,pid,name);
            lv.setAdapter(adapter);

        }
    }
    public void openProductSizesActivity(int pid) {
        Intent intent = new Intent(c,ProductSizes.class);
        intent.putExtra("PRODUCTID_KEY", pid);
       // intent.putExtra("PRODUCTNAME_KEY", name);
        c.startActivity(intent);
    }
    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            productTypesDBs.clear();
            ProductTypesDB productTypesDB;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);
                int ProductTypeId=jo.getInt("ProductTypeId");
                String ProductType =jo.getString("ProductType");
                String ImageUrl=jo.getString("ImageUrl");
                int ProductId = jo.getInt("ProductId");
                productTypesDB=new ProductTypesDB();
                productTypesDB.setProductTypeId(ProductTypeId);
                productTypesDB.setProductType(ProductType);
                productTypesDB.setImageUrl(ImageUrl);
                productTypesDB.setProductId(ProductId);
                productTypesDBs.add(productTypesDB);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
