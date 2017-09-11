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

public class DeleteGridProductTypes extends AppCompatActivity {
     ArrayList<MySQLDataBase> mySQLDataBases2;
    ArrayList<MySQLDataBase> mySQLDataBases1;
    private Spinner sp;
    private Button btnAdd;
    int click=0;
    private ArrayAdapter<MySQLDataBase> adapter ;
    private static final String DATA_DELETE_URL=Config.productTypeGridsCRUD;
    private static final String Data_spinner_url = Config.productTypeImgUrlAddress;
    private static  int productId,productTypeId;
    private static String recvdProName,recvdProType;
    URL gridProTypeSpinUrlAddress = null;
    ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grid_product_types);
  Intent in = getIntent();
        productId = in.getExtras().getInt("PRODUCTID_KEY");
        productTypeId = in.getExtras().getInt("PRODUCTTYPEID_KEY");
        recvdProName = in.getExtras().getString("PRODUCTNAME_KEY");
        recvdProType = in.getExtras().getString("PRODUCTTYPE_KEY");
        mySQLDataBases2 = (ArrayList<MySQLDataBase>) in.getSerializableExtra("ProductTypeGridList");
        mySQLDataBases1 = (ArrayList<MySQLDataBase>) in.getSerializableExtra("ProductTypeList");
        Uri builtUri = Uri.parse(Data_spinner_url)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(productId))
                .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(productTypeId))
                .build();

        try {
            gridProTypeSpinUrlAddress = new URL(builtUri.toString());
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
                        Intent in = new Intent(DeleteGridProductTypes.this, ProductTypesGridView.class);
                        // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                        in.putExtra("PRODUCTID_KEY", productId);
                        in.putExtra("PRODUCTNAME_KEY", recvdProName);
                        in.putExtra("PRODUCTTYPEID_KEY", productTypeId);
                        in.putExtra("PRODUCTTYPE_KEY", recvdProType);
                        in.putExtra("ProductTypeGridList",mySQLDataBases2);
                        in.putExtra("ProductTypeList",mySQLDataBases1);
                        setResult(Activity.RESULT_OK,in);
                        startActivity(in);*/
                        finish();
                       /* Intent in = new Intent(DeleteGridProductTypes.this, Refresh.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();*/
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
           /*Intent in = new Intent(DeleteGridProductTypes.this, ProductTypesGridView.class);
                        // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();*/
            Intent in = new Intent(DeleteGridProductTypes.this, ProductTypesGridView.class);
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);
            in.putExtra("PRODUCTID_KEY", productId);
            in.putExtra("PRODUCTNAME_KEY", recvdProName);
            in.putExtra("PRODUCTTYPEID_KEY", productTypeId);
            in.putExtra("PRODUCTTYPE_KEY", recvdProType);
            in.putExtra("ProductTypeGridList",mySQLDataBases2);
            in.putExtra("ProductTypeList",mySQLDataBases1);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);*/
            finish();
            super.onBackPressed();
        }
    }
    private void initializeViews()
    {
        btnAdd= (Button) findViewById(R.id.gridproducttypedelete);
        sp= (Spinner) findViewById(R.id.gridproducttypesp);
        //sp.setPrompt("Select One....");
    }
    private void handleClickEvents(final int prosizeimgid)

    {  click = click + 1;
        if (click == 1) {
            click = 0;
            //EVENTS : ADD
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //GET VALUES


                    //SAVE
                    MySQLDataBase s = new MySQLDataBase();
                    s.setProductSizeImageId(prosizeimgid);
                    if (s == null) {
                        Toast toast = Toast.makeText(DeleteGridProductTypes.this, "No Data To Delete", Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        // Toast.makeText(DeleteGridProductTypes.this, "No Data To Delete", Toast.LENGTH_SHORT).show();
                    } else {
                        AndroidNetworking.post(DATA_DELETE_URL)
                                .addBodyParameter("action", "delete")
                                .addBodyParameter("productsizeimageid", String.valueOf(s.getProductSizeImageId()))
                                .addBodyParameter("productname",recvdProName)
                                .addBodyParameter("producttype",recvdProType)
                                .setTag("TAG_ADD")
                                .build()
                                .getAsJSONArray(new JSONArrayRequestListener() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        if (response != null)
                                            try {
                                                //SHOW RESPONSE FROM SERVER
                                                String responseString = response.get(0).toString();
                                                Toast.makeText(DeleteGridProductTypes.this, " " + responseString, Toast.LENGTH_SHORT).show();
                                                if (responseString.equalsIgnoreCase("Successfully Deleted")) {

                                                    AlertDialog.Builder alert = new AlertDialog.Builder(DeleteGridProductTypes.this);
                                                    alert.setTitle(Html.fromHtml("<font color='#ff0000'>Information!</font>"));
                                                    alert.setMessage("To Refresh Newly added content Go Back to Home Screen..\n Confirm Delete By Clicking on OK");
                                                    //alert.setMessage("Confirm Delete By Clicking on OK");
                                                    alert.setIcon(R.drawable.reload);
                                                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Intent intent=new Intent(DeleteGridProductTypes.this,DeleteGridProductTypes.class);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                                                                    Intent.FLAG_ACTIVITY_NEW_TASK*/);
                                                            intent.putExtra("PRODUCTID_KEY", productId);
                                                            intent.putExtra("PRODUCTNAME_KEY", recvdProName);
                                                            intent.putExtra("PRODUCTTYPEID_KEY", productTypeId);
                                                            intent.putExtra("PRODUCTTYPE_KEY", recvdProType);
                                                            intent.putExtra("ProductTypeGridList",mySQLDataBases2);
                                                            intent.putExtra("ProductTypeList",mySQLDataBases1);
                                                            setResult(Activity.RESULT_OK,intent);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    });
                                                    alert.show();
                                                } else {
                                                    Toast.makeText(DeleteGridProductTypes.this, responseString, Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Toast.makeText(DeleteGridProductTypes.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                    }

                                    //ERROR
                                    @Override
                                    public void onError(ANError anError) {
                                        Toast.makeText(DeleteGridProductTypes.this, "UNSUCCESSFUL :  ERROR IS : " + anError.getMessage(), Toast.LENGTH_SHORT).show();
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
                HttpPost httppost = new HttpPost(String.valueOf(gridProTypeSpinUrlAddress));
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
                    int producttypeimageid = jo.getInt("ProductSizeImageId");
                    String imagename = jo.getString("Name");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setProductSizeImageId(producttypeimageid);
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

            adapter=new ArrayAdapter(DeleteGridProductTypes.this,R.layout.spinner_layout, R.id.txt,listItems);
            sp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    if(sp.getSelectedItem().toString().equals("Select One")){
                      /*  Toast.makeText(DeleteGridProductTypes.this,
                                "Your Selected : Nothing",
                                Toast.LENGTH_SHORT).show();*/
                    }else {
                        MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position-1);
                        final String name = mySQLDataBase.getName();
                        final int prosizeimgid = mySQLDataBase.getProductSizeImageId();
                        handleClickEvents(prosizeimgid);
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
                    Toast.makeText(DeleteGridProductTypes.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });

        }
    }

}