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
 * Created by Atwyn on 4/14/2017 for parsing database data and stored into ProductTypeItem array .
 */

public class ProductTypeSizesDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    String jsonData;

    ArrayList<ProductTypeSizeDBData> productTypeSizeDBDatas = new ArrayList<>();

    public ProductTypeSizesDataParser(Context c, ListView lv, String jsonData) {
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

            final ProductTypeSizesListAdapter adapter=new ProductTypeSizesListAdapter(c,productTypeSizeDBDatas);
            lv.setAdapter(adapter);
        }
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
                int SizeId=jo.getInt("SizeId");
                int Length =jo.getInt("Length");
                int Width = jo.getInt("Width");
                int Height = jo.getInt("Height");
                String Measure =jo.getString("Measurement");
                int ProductTypeId=jo.getInt("ProductTypeId");
                productTypeSizeDBData=new ProductTypeSizeDBData();

                productTypeSizeDBData.setSizeId(SizeId);
                productTypeSizeDBData.setLength(Length);
                productTypeSizeDBData.setWidth(Width);
                productTypeSizeDBData.setHeight(Height);
                productTypeSizeDBData.setMeasurement(Measure);
                productTypeSizeDBData.setProductTypeId(ProductTypeId);
                productTypeSizeDBDatas.add(productTypeSizeDBData);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
