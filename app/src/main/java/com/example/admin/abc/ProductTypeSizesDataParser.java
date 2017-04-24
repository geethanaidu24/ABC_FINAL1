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
 * Created by Atwyn on 4/14/2017 for parsing database data and stored into ProductTypeItem array .
 */

public class ProductTypeSizesDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    String jsonData;
    int pid;
    int ptid;

    ArrayList<ProductTypeSizeDBData> productTypeSizeDBDatas = new ArrayList<>();

    public ProductTypeSizesDataParser(Context c, ListView lv, String jsonData, int pid, int ptid) {
        this.c = c;
        this.lv = lv;
        this.jsonData = jsonData;
        this.pid = pid;
        this.ptid=  ptid;
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
           // Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
            openGridViewActivity();

        }else
        {

            final ProductTypeSizesListAdapter adapter=new ProductTypeSizesListAdapter(c,productTypeSizeDBDatas);
            lv.setAdapter(adapter);
        }
    }
    public void openGridViewActivity() {
        Intent intent = new Intent(c,ProductTypeImages.class);
        intent.putExtra("PRODUCTID_KEY",pid);
        intent.putExtra("PRODUCTTYPEID_KEY", ptid);
        c.startActivity(intent);
    }
    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            productTypeSizeDBDatas.clear();
            ProductTypeSizeDBData productTypeSizeDBData;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);
                int ProductSizeId=jo.getInt("ProductSizeId");
                int Width = jo.getInt("Width");
                int Height = jo.getInt("Height");
                int Length =jo.getInt("Length");

               // String Measure =jo.getString("Measurement");
                int ProductTypeId=jo.getInt("ProductTypeId");
               // int ProductTypeId=jo.getInt("ProductTypeId");
                int ProductId = jo.getInt("ProductId");
                productTypeSizeDBData=new ProductTypeSizeDBData();

                productTypeSizeDBData.setProductSizeId(ProductSizeId);
                productTypeSizeDBData.setWidth(Width);
                productTypeSizeDBData.setHeight(Height);
                productTypeSizeDBData.setLength(Length);

                //productTypeSizeDBData.setMeasurement(Measure);
                productTypeSizeDBData.setProductTypeId(ProductTypeId);
                productTypeSizeDBData.setProductId(ProductId);
                productTypeSizeDBDatas.add(productTypeSizeDBData);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
