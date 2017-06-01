package com.example.admin.abc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class DeleteGridProductTypeSizes extends AppCompatActivity {
    final ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();
    private Spinner sp;
    private Button btnAdd;
 int recivedProductId,recivedProductTypeId,recivedProductsizeID;
    private ArrayAdapter<MySQLDataBase> adapter ;
    private static final String DATA_DELETE_URL=Config.producttypeSizesGridsCRUD;
    final static String DelProTypeSizeurl =Config.productTypeSizeImgUrlAddress;
    URL delGridProTypeSizeSpin = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grid_product_type_sizes);

        Intent intent = getIntent();
        recivedProductId = intent.getExtras().getInt("PRODUCTID_KEY");
         recivedProductTypeId = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        recivedProductsizeID=intent.getExtras().getInt("PRODUCTTYPESIZEID_KEY");
        Uri builtUri = Uri.parse(DelProTypeSizeurl)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(recivedProductId))
                .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(recivedProductTypeId))
                .appendQueryParameter(Config.PRODUCTSIZEID_PARAM, Integer.toString(recivedProductsizeID))
                .build();

        try {
            delGridProTypeSizeSpin = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(DeleteGridProductTypeSizes.this, Main2Activity.class);
                   // finish();
                    // in.putExtra("PRODUCTID_KEY", pid);
                     startActivity(in);
                }
            });

        }
        this.initializeViews();
    }
    private void initializeViews()
    {


        btnAdd= (Button) findViewById(R.id.delete2);
        sp= (Spinner) findViewById(R.id.sp2);
        //sp.setPrompt("Select One.....");
    }
    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents(final int protypesizeid)
    {
        //EVENTS : ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET VALUES


                //SAVE
                MySQLDataBase s=new MySQLDataBase();
                s.setProductSizeImageId(protypesizeid);
                if(s==null)
                {
                    Toast.makeText(DeleteGridProductTypeSizes.this, "No Data To Delete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AndroidNetworking.post(DATA_DELETE_URL)
                            .addBodyParameter("action","delete")
                            .addBodyParameter("productsizeimageid", String.valueOf(s.getProductSizeImageId()))
                            .setTag("TAG_ADD")
                            .build()
                            .getAsJSONArray(new JSONArrayRequestListener() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if(response != null)
                                        try {
                                            //SHOW RESPONSE FROM SERVER
                                            String responseString = response.get(0).toString();
                                            Toast.makeText(DeleteGridProductTypeSizes.this, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();
                                            if (responseString.equalsIgnoreCase("Successfully Deleted")) {
                                                Intent intent = new Intent(DeleteGridProductTypeSizes.this,DeleteGridProductTypeSizes.class);
                                                intent.putExtra("PRODUCTTYPEID_KEY", recivedProductTypeId);
                                                intent.putExtra("PRODUCTID_KEY",recivedProductId);
                                                intent.putExtra("PRODUCTTYPESIZEID_KEY",recivedProductsizeID);
                                                startActivity(intent);
   /* adapter.notifyDataSetChanged();
    BackTask bt = new BackTask();
    bt.execute();*/
                                            }else {
                                                Toast.makeText(DeleteGridProductTypeSizes.this, responseString, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(DeleteGridProductTypeSizes.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                }
                                //ERROR
                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(DeleteGridProductTypeSizes.this, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
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


        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(String.valueOf(delGridProTypeSizeSpin));
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
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < ja.length(); i++) {
                    jo=ja.getJSONObject(i);
                    // add interviewee name to arraylist
                    int psiid = jo.getInt("ProductSizeImageId");
                    String pname = jo.getString("Name");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setProductSizeImageId(psiid);

                    mySQLDataBase.setName(pname);
                    mySQLDataBases.add(mySQLDataBase);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            // productcrafts.addAll(productcrafts);
            final ArrayList<String> listItems = new ArrayList<>();
            listItems.add("Select One");
            for(int i=0;i<mySQLDataBases.size();i++){
                listItems.add(mySQLDataBases.get(i).getName());
            }

            adapter=new ArrayAdapter(DeleteGridProductTypeSizes.this,R.layout.spinner_layout, R.id.txt,listItems);
            sp.setAdapter(adapter);
            // adapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    if(sp.getSelectedItem().toString().equals("Select One")){
                        Toast.makeText(DeleteGridProductTypeSizes.this,
                                "Your Selected : Nothing",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position-1);
                        final String name = mySQLDataBase.getName();
                        //  final int pid
                        final int psiid = mySQLDataBase.getProductSizeImageId();
                        handleClickEvents(psiid);
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                            }
                        };
                    }
                }
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DeleteGridProductTypeSizes.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });

        }
    }

}