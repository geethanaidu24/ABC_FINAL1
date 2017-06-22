package com.example.admin.abc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DeleteProducts extends AppCompatActivity {
    final ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();
    private Spinner sp;
    int click=0;
    private Button btnAdd,btnSpin;
    private ArrayAdapter<MySQLDataBase> adapter ;
    private static final String DATA_DELETE_URL=Config.productsCRUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        Intent in = new Intent(DeleteProducts.this, Refresh.class);
                        //in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        finish();
                       /* startActivity(in);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);*/
                    }
                    //finish();
                }
            });

        }
        this.initializeViews();
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(DeleteProducts.this, Refresh.class);
            // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            finish();
            /*startActivity(in);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);*/
            //finish();
        }
    }
    private void initializeViews()
    {


        btnAdd= (Button) findViewById(R.id.addBtn);
        sp= (Spinner) findViewById(R.id.spinner);

    }
    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents(final int pid)
    {
        //EVENTS : ADD
        click = click + 1;
        if (click == 1) {
            click = 0;
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sp.getSelectedItem().equals("Select One")) {

                        Toast.makeText(DeleteProducts.this,
                                "Your Selected : Nothing",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        //SAVE
                        // final int pos = sp.getSelectedItemPosition();
                        MySQLDataBase s = new MySQLDataBase();
                        s.setProductId(pid);
                        if (s == null) {
                            Toast toast = Toast.makeText(DeleteProducts.this, "No Data To Delete", Toast.LENGTH_SHORT);

                            View toastView = toast.getView();
                            toastView.setBackgroundResource(R.drawable.toast_drawable);
                            toast.show();
                            //Toast.makeText(DeleteProducts.this, "No Data To Delete", Toast.LENGTH_SHORT).show();
                        } else {

                            AndroidNetworking.post(DATA_DELETE_URL)
                                    .addBodyParameter("action", "delete")
                                    .addBodyParameter("productid", String.valueOf(s.getProductId()))
                                    .setTag("TAG_ADD")
                                    .build()
                                    .getAsJSONArray(new JSONArrayRequestListener() {
                                        @Override
                                        public void onResponse(JSONArray response) {
                                            if (response != null)
                                                try {
                                                    //SHOW RESPONSE FROM SERVER
                                                    String responseString = response.get(0).toString();
                                                    Toast.makeText(DeleteProducts.this, responseString, Toast.LENGTH_SHORT).show();
                                                    if (responseString.equalsIgnoreCase("Successfully Deleted")) {
                                                        Intent intent = new Intent(DeleteProducts.this, DeleteProducts.class);
                                                        startActivity(intent);



                                              /*  new AlertDialog.Builder(DeleteProducts.this) .setTitle(Html.fromHtml("<font color='#ff0000'>Exit</font>"))

                                                        .setMessage(Html.fromHtml(" It will be taking time to Refresh"));*/

                                                        //  Toast.makeText(DeleteProducts.this, "It will be taking time to load", Toast.LENGTH_LONG).show();
                                               /* adapter.notifyDataSetChanged();
                                                BackTask bt = new BackTask();
                                                bt.execute();*/
                                                    } else {
                                                        Toast.makeText(DeleteProducts.this, responseString, Toast.LENGTH_SHORT).show();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    Toast.makeText(DeleteProducts.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                        }

                                        //ERROR
                                        @Override
                                        public void onError(ANError anError) {
                                            Toast.makeText(DeleteProducts.this, "UNSUCCESSFUL :  ERROR IS : " + anError.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                }
            });
        }

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
                HttpPost httppost = new HttpPost(Config.productsUrlAddress);
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
                    int pid = jo.getInt("ProductId");
                    String pname = jo.getString("ProductName");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setProductId(pid);
                    mySQLDataBase.setProductName(pname);
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
                listItems.add(mySQLDataBases.get(i).getProductName());

            }
            adapter=new ArrayAdapter(DeleteProducts.this,R.layout.spinner_layout, R.id.txt,listItems);
            sp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {

                    if(sp.getSelectedItem().equals("Select One")){
/*
                        Toast.makeText(DeleteProducts.this,
                                "Your Selected : Nothing",
                                Toast.LENGTH_SHORT).show();*/
                    }else {

                        MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position - 1);

                        //  final int pid
                        final int pid = mySQLDataBase.getProductId();
                        Log.d("selected id", "" + pid);
                        handleClickEvents(pid);
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
                    Toast.makeText(DeleteProducts.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });

        }

    }

}
