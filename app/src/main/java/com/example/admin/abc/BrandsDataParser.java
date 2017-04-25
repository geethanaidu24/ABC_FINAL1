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
 * Created by Admin on 4/18/2017.
 */

class BrandsDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;

    GridView gridView1;
    String jsonData;

    ArrayList<BrandsImages> brandsImages=new ArrayList<>();
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

            final BrandsListAdapter adapter=new BrandsListAdapter(c,brandsImages);
            gridView1.setAdapter(adapter);
        }
    }

    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            brandsImages.clear();
            BrandsImages brandsImage;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);
                int BrandId=jo.getInt("ID");
                String ImageUrl=jo.getString("ImagePath");
                brandsImage=new BrandsImages();
                brandsImage.setId(BrandId);

                brandsImage.setImagePath(ImageUrl);
                brandsImages.add(brandsImage);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
