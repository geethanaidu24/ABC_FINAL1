package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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
import java.util.HashSet;


/**
 * Created by Atwyn on 5/5/2017.
 */

public class AddProductTypeSizes extends AppCompatActivity implements View.OnClickListener {

    private static final String DATA_INSERT_URL = Config.productTypeSizesCRUD;
    private EditText txtwidth, txtheight, txtlength;
    private TextView displaySelectedProductType,displaySelectedProduct;
    Context context;
    private Button btnAdd;
    private static int selectedProdutId1, selectedProdutTypeId1;
    private static String selectedProductName1, selectedProductType1;
   /* final ArrayList<SizesDB> sizesDBs = new ArrayList<>();
    private Spinner sp1, sp2;
    private Button btnAdd;
    private ArrayAdapter<SizesDB> adapter;
    private ArrayAdapter<SizesDB> adapter1;
    public int ipid=0;
    public int iptid=0;*/

        @Override
        protected void onCreate(Bundle savedInstanceState) {
           // getSupportActionBar().hide();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_product_type_sizes);
            Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
            selectedProdutId1 = intent.getExtras().getInt("PRODUCTID_KEY");
            selectedProductName1 = intent.getExtras().getString("PRODUCTNAME_KEY");
            selectedProdutTypeId1 = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
            selectedProductType1 = intent.getExtras().getString("PRODUCTTYPE_KEY");
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (null != toolbar) {
                toolbar.setNavigationIcon(R.mipmap.backbutton);

                //  actionbar.setTitle(R.string.title_activity_settings);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(AddProductTypeSizes.this,Main2Activity.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                       // finish();
                        startActivity(in);
                    }
                });

            }

            txtwidth = (EditText) findViewById(R.id.width1);
            txtwidth.setInputType(InputType.TYPE_CLASS_TEXT);
            txtheight = (EditText) findViewById(R.id.height1);
            txtheight.setInputType(InputType.TYPE_CLASS_TEXT);
            txtlength = (EditText) findViewById(R.id.length1);
            txtlength.setInputType(InputType.TYPE_CLASS_TEXT);
            btnAdd = (Button) findViewById(R.id.brandbtn);
            displaySelectedProductType =(TextView) findViewById(R.id.protypeDisplay);
            displaySelectedProduct =(TextView)findViewById(R.id.proDisplay);

            displaySelectedProduct.setText(selectedProductName1);
            displaySelectedProductType.setText(selectedProductType1);

          /*  sp1 = (Spinner) findViewById(R.id.productsspinner);
            sp2 = (Spinner) findViewById(R.id.spinner8);*/
            btnAdd.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        if(view == btnAdd){
            checkData();
            //for checking empty values
        }
    }

    private void checkData() {
        if (txtwidth.length() < 1 || txtheight.length() < 1) {
            Toast.makeText(AddProductTypeSizes.this, "Fill All", Toast.LENGTH_SHORT).show();
        } else {
            uploadMultipart();
            Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
            txtwidth.setText("");
            txtheight.setText("");
            txtlength.setText("0");
            /*adapter.notifyDataSetChanged();
            adapter1.notifyDataSetChanged();
            BackTask bt = new BackTask();
            bt.execute();*/

        }

    }


    public void uploadMultipart() {

        String width=txtwidth.getText().toString();
        int wid=Integer.parseInt(width);

        String height=txtheight.getText().toString();
        int heig=Integer.parseInt(height);

        String length=txtlength.getText().toString();
        int leng=Integer.parseInt(length);



        //Uploading code
        try {
            AndroidNetworking.post(DATA_INSERT_URL)
                    .addBodyParameter("action","save")
                    .addBodyParameter("width", String.valueOf(wid))
                    .addBodyParameter("height",String.valueOf(heig))
                    .addBodyParameter("length",String.valueOf(length))
                   // .addBodyParameter("productid", String.valueOf(ipid))
                    .addBodyParameter("productid", String.valueOf(selectedProdutId1))
                    .addBodyParameter("producttypeid",String.valueOf(selectedProdutTypeId1))
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                    Toast.makeText(AddProductTypeSizes.this, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();
                                    if (responseString.equalsIgnoreCase("Success")) {
                                        //CLEAR EDITXTS
                                       /* txtwidth.setText("");
                                        txtheight.setText("");
                                        txtlength.setText("");*/
                                       /* adapter.notifyDataSetChanged();
                                        adapter1.notifyDataSetChanged();
                                        BackTask bt = new BackTask();
                                        bt.execute();*/

                                    }else
                                    {
                                        Toast.makeText(AddProductTypeSizes.this, "PHP WASN'T SUCCESSFUL. ", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AddProductTypeSizes.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }
                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(AddProductTypeSizes.this, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
