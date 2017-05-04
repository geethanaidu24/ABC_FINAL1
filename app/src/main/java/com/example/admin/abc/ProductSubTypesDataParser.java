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
 * Created by Geetha on 4/20/2017 for storing database data into an array.
 */

public class ProductSubTypesDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    LinearLayout ll;
    String jsonData;
    int ptid;
    int pid;
    String pname, ptname;
    ArrayList<ProductSubTypesDB> productSubTypesDBs=new ArrayList<>();

    public ProductSubTypesDataParser(Context c, ListView lv,LinearLayout ll, String jsonData, int pid, String pname, int ptid, String ptname) {
        this.c = c;
        this.lv = lv;
        this.ll=ll;
        this.jsonData = jsonData;
        this.ptid = ptid;
        this.pid = pid;
        this.pname = pname;
        this.ptname = ptname;
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
            openProductTypesSizesActivity(pid,pname,ptid,ll);
        }else
        {
            ll.setVisibility(View.VISIBLE);
            final ProductSubTypesListAdapter adapter=new ProductSubTypesListAdapter(c,productSubTypesDBs,pid,pname,ptid,ptname);
            lv.setAdapter(adapter);

        }
    }
    public void openProductTypesSizesActivity(int pid,String pname,int ptid,LinearLayout ll) {
        Intent intent = new Intent(c,ProductTypeSizes.class);
        intent.putExtra("PRODUCTID_KEY",pid);
        intent.putExtra("PRODUCTNAME_KEY",pname);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        c.startActivity(intent);
    }
    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            productSubTypesDBs.clear();
            ProductSubTypesDB productSubTypesDB;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);
                int ProductSubTypeId=jo.getInt("ProductSubTypeId");
                String ProductSubTypeName =jo.getString("ProductSubTypeName");
                String ImageUrl=jo.getString("ImageUrl");
                int ProductTypeId=jo.getInt("ProductTypeId");
                productSubTypesDB=new ProductSubTypesDB();
                productSubTypesDB.setProductSubTypeId(ProductSubTypeId);
                productSubTypesDB.setProductSubTypeName(ProductSubTypeName);
                productSubTypesDB.setImageUrl(ImageUrl);
                productSubTypesDB.setProductTypeId(ProductTypeId);
                productSubTypesDBs.add(productSubTypesDB);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
