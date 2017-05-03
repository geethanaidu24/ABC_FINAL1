package com.example.admin.abc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;

public class DeleteProducts extends AppCompatActivity {
    final ArrayList<Productcraft> productcrafts = new ArrayList<>();
    // ArrayAdapter<String> adapter;
    private Spinner sp;
    private Button btnAdd;
    private ArrayAdapter<Productcraft> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_products);
        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(DeleteProducts.this, Products.class);
                    startActivity(in);
                }
            });

        }
        this.initializeViews();
    }
    private void initializeViews()
    {

        //txtPropellant= (TextInputEditText) findViewById(R.id.propellantTxt);

        btnAdd= (Button) findViewById(R.id.addBtn);
        // btnRetrieve= (Button) findViewById(R.id.refreshBtn);
        sp= (Spinner) findViewById(R.id.sp);
    }
    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents(final int pid)
    {
        //EVENTS : ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET VALUES

                String spinSelVal = sp.getSelectedItem().toString();

                final int rpid = pid;

                    //SAVE
                    Productcraft s=new Productcraft();

                    // s.setPname();

                    s.setPRODUCTID(rpid);
                    new MySQLClient(DeleteProducts.this).add(s,spinSelVal);

            }
        });

    }
    public void onStart() {
        super.onStart();
        BackTask bt = new BackTask();
        bt.execute();
    }

    private class BackTask extends AsyncTask<Void, Void, Void> {
        ArrayList<String> list;

        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2/abc/deleteproducts.php");
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
                productcrafts.clear();
                Productcraft productcraft;
                for (int i = 0; i < ja.length(); i++) {
                    jo=ja.getJSONObject(i);
                    // add interviewee name to arraylist
                    int pid = jo.getInt("ProductId");
                    String pname = jo.getString("ProductName");
                    productcraft=new Productcraft();
                    productcraft.setPRODUCTID(pid);
                    productcraft.setPRODUCTNAME(pname);
                    productcrafts.add(productcraft);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            // productcrafts.addAll(productcrafts);
            final ArrayList<String> listItems = new ArrayList<>();
            for(int i=0;i<productcrafts.size();i++){
                listItems.add(productcrafts.get(i).getPRODUCTNAME());
            }
            //listItems.addAll(list);
            // SpinnerAdapter spinnerAdapter = new SpinnerAdapter(MainActivity.this,productcrafts);
            // sp.setAdapter(spinnerAdapter);
            adapter=new ArrayAdapter(DeleteProducts.this,R.layout.spinner_layout, R.id.txt,listItems);
            sp.setAdapter(adapter);
            // adapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    Productcraft productcraft = (Productcraft)productcrafts.get(position);
                    final String name = productcraft.getPRODUCTNAME();
                    //  final int pid
                    final int pid =productcraft.getPRODUCTID() ;
                    handleClickEvents(pid);
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
                    Toast.makeText(DeleteProducts.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });


        }
    }

}
