package com.atwyn.android.abc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
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

public class DeleteGridSubTypes extends AppCompatActivity {
    final ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();
    private Spinner sp;
    int click=0;
    private Button btnAdd;
    private static int productSubTypeId,selectedProducttypeid,selectedPid;
    private static String recvProName,recvProType,recvSubType;
    private ArrayAdapter<MySQLDataBase> adapter ;
    private static final String DATA_DELETE_URL=Config.productSubTypeGridsCRUD;
    private static final String Data_spinner_url = Config.productSubTypeGridUrlAddress;
    URL ProSubTypeGridurlAddress = null;
    ArrayList<MySQLDataBase> mySQLDataBases1;
    ArrayList<MySQLDataBase> mySQLProTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grid_sub_types);
        Intent in = getIntent();
        productSubTypeId = in.getExtras().getInt("PRODUCTSUBTYPEID_KEY");
        recvProName = in.getExtras().getString("PRODUCTNAME_KEY");
        recvProType = in.getExtras().getString("PRODUCTTYPE_KEY");
        recvSubType = in.getExtras().getString("PRODUCTSUBTYPENAME_KEY");

        selectedProducttypeid = in.getExtras().getInt("PRODUCTTYPEID_KEY");
        selectedPid= in.getExtras().getInt("PRODUCTID_KEY");
        mySQLDataBases1 = (ArrayList<MySQLDataBase>) in.getSerializableExtra("ProductSubTypeList");

        mySQLProTypes = (ArrayList<MySQLDataBase>) in.getSerializableExtra("ProductTypeList") ;
        Uri builtUri = Uri.parse(Data_spinner_url)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTSUBTYPEID_PARAM, Integer.toString(productSubTypeId))
                .build();

        try {
            ProSubTypeGridurlAddress = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        Intent in = new Intent(DeleteGridSubTypes.this, ProductSubTypeGridView.class);
                        //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        // finish();

                        /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                        in.putExtra("PRODUCTSUBTYPENAME_KEY", recvSubType);
                        in.putExtra("PRODUCTSUBTYPEID_KEY", productSubTypeId);
                        in.putExtra("PRODUCTID_KEY", selectedPid);
                        in.putExtra("PRODUCTNAME_KEY", recvProName);
                        in.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
                        in.putExtra("PRODUCTTYPE_KEY", recvProType);
                        in.putExtra("ProductSubTypeList",mySQLDataBases1);
                        in.putExtra("ProductTypeList",mySQLProTypes);
                        setResult(Activity.RESULT_OK,in);
                        startActivity(in);*/
                        finish();
                    }
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
            Intent in = new Intent(DeleteGridSubTypes.this, ProductSubTypeGridView.class);
            //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            // finish();
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);
            in.putExtra("PRODUCTSUBTYPENAME_KEY", recvSubType);
            in.putExtra("PRODUCTSUBTYPEID_KEY", productSubTypeId);
            in.putExtra("PRODUCTID_KEY", selectedPid);
            in.putExtra("PRODUCTNAME_KEY", recvProName);
            in.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
            in.putExtra("PRODUCTTYPE_KEY", recvProType);
            in.putExtra("ProductSubTypeList",mySQLDataBases1);
            in.putExtra("ProductTypeList",mySQLProTypes);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);*/
            finish();
        }
    }
    private void initializeViews()
    {
        btnAdd= (Button) findViewById(R.id.griddelete);
        sp= (Spinner) findViewById(R.id.gridsp);
    }
    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents(final int prosizeimgid)
    {  click = click + 1;
        if (click == 1) {
            click = 0;
            //EVENTS : ADD
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //GET VALUES
                    final int recivedprosizeimgid = prosizeimgid;
                    //SAVE
                    MySQLDataBase s = new MySQLDataBase();
                    s.setProductSizeImageId(recivedprosizeimgid);
                    if (s == null) {
                        Toast toast = Toast.makeText(DeleteGridSubTypes.this, "No Data To Delete", Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        //Toast.makeText(DeleteGridSubTypes.this, "No Data To Delete", Toast.LENGTH_SHORT).show();
                    } else {
                        AndroidNetworking.post(DATA_DELETE_URL)
                                .addBodyParameter("action", "delete")
                                .addBodyParameter("productsizeimageid", String.valueOf(s.getProductSizeImageId()))
                                .addBodyParameter("productname",recvProName)
                                .addBodyParameter("producttype",recvProType)
                                .addBodyParameter("productsubtype",recvSubType)
                                .setTag("TAG_ADD")
                                .build()
                                .getAsJSONArray(new JSONArrayRequestListener() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        if (response != null)
                                            try {
                                                //SHOW RESPONSE FROM SERVER
                                                String responseString = response.get(0).toString();
                                                Toast.makeText(DeleteGridSubTypes.this, " " + responseString, Toast.LENGTH_SHORT).show();
                                                if (responseString.equalsIgnoreCase("Successfully Deleted")) {
                                                   /* Intent intent = new Intent(DeleteGridSubTypes.this, DeleteGridSubTypes.class);
                                                    intent.putExtra("PRODUCTSUBTYPEID_KEY", productSubTypeId);
                                                    intent.putExtra("PRODUCTNAME_KEY", recvProName);
                                                    intent.putExtra("PRODUCTTYPE_KEY", recvProType);
                                                    intent.putExtra("PRODUCTSUBTYPENAME_KEY", recvSubType);
                                                    startActivity(intent);*/
                                                    AlertDialog.Builder alert = new AlertDialog.Builder(DeleteGridSubTypes.this);
                                                    alert.setTitle(Html.fromHtml("<font color='#ff0000'>Information!</font>"));
                                                    alert.setMessage("To Refresh Newly added content Go Back to Home Screen..\n Confirm Delete By Clicking on OK");
                                                    //alert.setMessage("Confirm Delete By Clicking on OK");
                                                    alert.setIcon(R.drawable.reload);
                                                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Intent in=new Intent(DeleteGridSubTypes.this,DeleteGridSubTypes.class);
                                                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                                                                    Intent.FLAG_ACTIVITY_NEW_TASK*/);
                                                            in.putExtra("PRODUCTSUBTYPENAME_KEY", recvSubType);
                                                            in.putExtra("PRODUCTSUBTYPEID_KEY", productSubTypeId);
                                                            in.putExtra("PRODUCTID_KEY", selectedPid);
                                                            in.putExtra("PRODUCTNAME_KEY", recvProName);
                                                            in.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
                                                            in.putExtra("PRODUCTTYPE_KEY", recvProType);
                                                            in.putExtra("ProductSubTypeList",mySQLDataBases1);
                                                            in.putExtra("ProductTypeList",mySQLProTypes);
                                                            setResult(Activity.RESULT_OK,in);
                                                            startActivity(in);
                                                            finish();
                                                        }
                                                    });
                                                    alert.show();
                                                } else {
                                                    Toast.makeText(DeleteGridSubTypes.this, responseString, Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Toast.makeText(DeleteGridSubTypes.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                    }

                                    //ERROR
                                    @Override
                                    public void onError(ANError anError) {
                                        Toast.makeText(DeleteGridSubTypes.this, "UNSUCCESSFUL :  ERROR IS : " + anError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
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
        // for spinner
        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(String.valueOf(ProSubTypeGridurlAddress));
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
                    int productsubtypeimageid = jo.getInt("ProductSizeImageId");
                    String imagename = jo.getString("Name");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setProductSizeImageId(productsubtypeimageid);
                    mySQLDataBase.setName(imagename);
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

            adapter= new ArrayAdapter(DeleteGridSubTypes.this,R.layout.spinner_layout, R.id.txt,listItems);
            sp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    if(sp.getSelectedItem().toString().equals("Select One")){
/*
                        Toast.makeText(DeleteGridSubTypes.this,
                                "Your Selected : Nothing",
                                Toast.LENGTH_SHORT).show();*/
                    }else {

                    MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position-1);
                    final String name = mySQLDataBase.getName();
                    final int prosizeimgid =mySQLDataBase.getProductSizeImageId() ;
                    handleClickEvents(prosizeimgid);
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();

                        }
                    };
                }}
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DeleteGridSubTypes.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });

        }
    }

}