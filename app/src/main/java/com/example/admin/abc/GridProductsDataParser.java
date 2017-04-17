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
 * Created by Geetha on 4/12/2017.
 */

public class GridProductsDataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    //GridView gv;
    String jsonData;

    ArrayList<ProductImages> productImages=new ArrayList<>();
    public GridProductsDataParser(Context c, ListView lv, String jsonData) {
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

            final ImageListAdapterClass adapter=new ImageListAdapterClass(c,productImages);
            lv.setAdapter(adapter);

           /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent =null;
                    String poid = parent.getItemAtPosition(position).toString();



                    int pId = (int) parent.getItemIdAtPosition(position);



                     intent = new Intent(c,ProductsFinalDetailsView.class);

                    intent.putExtra("PRODUCTIDPOSITION_KEY", poid);
                    intent.putExtra("PRODUCTID_KEY",pId);
                    c.startActivity(intent);
                }
            });*/

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
