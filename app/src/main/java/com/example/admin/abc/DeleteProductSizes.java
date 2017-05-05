package com.example.admin.abc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DeleteProductSizes extends AppCompatActivity {
    final ArrayList<SizesDB> sizesDBs = new ArrayList<>();
    private Spinner sp;
    private Button btnAdd;
    private ArrayAdapter<SizesDB> adapter ;
    private static final String DATA_DELETE_URL=Config.productSizesCRUD;
    private static final String DATA_Size_Spin = Config.productTypeSizesUrlAddress;
    URL DATA_Spinner = null;
    String finalSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product_sizes);
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
        final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        Uri builtUri = Uri.parse(DATA_Size_Spin)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(pid))

                .build();

        try {
            DATA_Spinner = new URL(builtUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(DeleteProductSizes.this, ProductSizes.class);
                    in.putExtra("PRODUCTID_KEY", pid);
                    startActivity(in);
                }
            });

        }
        this.initializeViews();
    }
    private void initializeViews()
    {


        btnAdd= (Button) findViewById(R.id.deletebtn1);
        sp= (Spinner) findViewById(R.id.spdelete1);
    }
    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents(final int psid)
    {
        //EVENTS : ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET VALUES

                //String spinSelVal = sp.getSelectedItem().toString();

                final int rpsid = psid;

                //SAVE
                SizesDB s=new SizesDB();
                s.setProductSizeId(rpsid);
                if(s==null)
                {
                    Toast.makeText(DeleteProductSizes.this, "No Data To Delete", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    AndroidNetworking.post(DATA_DELETE_URL)
                            .addBodyParameter("action","delete")
                            .addBodyParameter("productsizeid", String.valueOf(s.getProductSizeId()))
                            .setTag("TAG_ADD")
                            .build()
                            .getAsJSONArray(new JSONArrayRequestListener() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if(response != null)
                                        try {
                                            //SHOW RESPONSE FROM SERVER
                                            String responseString = response.get(0).toString();
                                            Toast.makeText(DeleteProductSizes.this, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();
                                            if (responseString.equalsIgnoreCase("Success")) {
                                                //CLEAR EDITXTS

                                            }else
                                            {
                                                adapter.notifyDataSetChanged();
                                               BackTask bt = new BackTask();
                                                bt.execute();
                                                //Toast.makeText(DeleteProductTypes.this, "PHP WASN'T SUCCESSFUL. ", Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(DeleteProductSizes.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                }
                                //ERROR
                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(DeleteProductSizes.this, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

    }
    public void onStart() {
        super.onStart();
       BackTask bt = new BackTask();
        bt.execute();
    }

    private class BackTask extends AsyncTask<Void, Void, Void> {
        //  ArrayList<String> list;

        protected void onPreExecute() {
            super.onPreExecute();
            // list = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(String.valueOf(DATA_Spinner));
                org.apache.http.HttpResponse response = httpclient.execute(httppost);
                org.apache.http.HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                JSONArray ja = new JSONArray(result);
                JSONObject jo=null;
                sizesDBs.clear();
                SizesDB sizesDB;
                for (int i = 0; i < ja.length(); i++) {
                    jo=ja.getJSONObject(i);
                    // add interviewee name to arraylist
                    int psid = jo.getInt("ProductSizeId");
                    int width = jo.getInt("Width");
                    int height =jo.getInt("Height");
                    int length =jo.getInt("Length");


                    sizesDB=new SizesDB();
                    sizesDB.setProductSizeId(psid);
                    sizesDB.setWidth(width);
                    sizesDB.setHeight(height);
                    sizesDB.setLength(length);
                    sizesDBs.add(sizesDB);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {


            final ArrayList<String> listItems = new ArrayList<>();
            for(int i=0;i<sizesDBs.size();i++){

                final int width = Integer.parseInt(String.valueOf(sizesDBs.get(i).getWidth()).toString());
                final int height = Integer.parseInt(String.valueOf(sizesDBs.get(i).getHeight()).toString());
                final int length = Integer.parseInt(String.valueOf(sizesDBs.get(i).getLength()).toString());
                //final String measure =productTypeSizeDBData.getMeasurement().toString();

                if(length !=0 && width !=0 && height !=0){
                    finalSize =  width + "X" + height + "X" + length;
                    listItems.add(String.valueOf(finalSize));

                }else if(length ==0 && width !=0 && height !=0){
                    finalSize =  width + "X" + height;
                    listItems.add(String.valueOf(finalSize));
                }else if(length !=0 && width ==0 && height !=0){
                    finalSize =  length + "X" + height;
                    listItems.add(String.valueOf(finalSize));
                }else if(length !=0 && width !=0 && height ==0 ){
                    finalSize =  length + "X" + width ;
                    listItems.add(String.valueOf(finalSize));
                }else if(length ==0 && width !=0 && height ==0 ){
                    finalSize = width + "" ;
                    listItems.add(String.valueOf(finalSize));
                }else if(length !=0 && width ==0 && height ==0 ){
                    finalSize = length + "" ;
                    listItems.add(String.valueOf(finalSize));
                }else if(length ==0 && width ==0 && height !=0 ){
                    finalSize = height + "" ;
                    listItems.add(String.valueOf(finalSize));
                }

            }

            adapter=new ArrayAdapter(DeleteProductSizes.this,R.layout.spinner_layout, R.id.txt,listItems);
            sp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {

                    SizesDB sizesDB = (SizesDB) sizesDBs.get(position);
                    final int psid =sizesDB.getProductSizeId();
                    Log.d("selected response: ", "> " + psid);
                    handleClickEvents(psid);
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                        }
                    };
                }
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DeleteProductSizes.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });


        }
    }

}

