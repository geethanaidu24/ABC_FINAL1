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
 * Created by Geetha on 4/8/2017.
 */

public class GridDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    GridView gv;
    String jsonData;

    ArrayList<BasinImages> basinImages=new ArrayList<>();
    public GridDataParser(Context c, GridView gv, String jsonData) {
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
            BasinImageListAdapterClass adapter=new BasinImageListAdapterClass(c,basinImages);
            gv.setAdapter(adapter);
        }
    }
    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            basinImages.clear();
            BasinImages basinImage;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Log.d("result response: ", "> " + jo);
                int id=jo.getInt("ImageId");
                String name =jo.getString("Name");
                String url=jo.getString("ImagePath");
                String brand = jo.getString("Brands");
                String color = jo.getString("Color");
                int sizeid = jo.getInt("SizeId");
                basinImage=new BasinImages();
                basinImage.setId(id);
                basinImage.setName(name);
                basinImage.setUrl(url);
                basinImage.setBrands(brand);
                basinImage.setColor(color);
                basinImage.setSizeid(sizeid);
                basinImages.add(basinImage);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
