package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AddProductSizes extends AppCompatActivity implements View.OnClickListener {
    private static final String UPLOAD_URL = Config.productSizesCRUD;
    private EditText txtwidth, txtheight, txtlength;
    private TextView displayProducts;
    Context context;
    /*final ArrayList<SizesDB> sizesDBs = new ArrayList<>();
    private Spinner sp1;*/
    private Button btnAdd1;
    private static int selectdPId;
    private static String selectdProName;
   /* private ArrayAdapter<SizesDB> adapter;*/

   // public int ipid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_sizes);
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
       selectdPId= intent.getExtras().getInt("PRODUCTID_KEY");
       selectdProName = intent.getExtras().getString("PRODUCTNAME_KEY");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(AddProductSizes.this,Main2Activity.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                   // in.putExtra("PRODUCTID_KEY", pid);
                    startActivity(in);
                   // finish();
                }
            });

        }

        txtwidth = (EditText) findViewById(R.id.width1);
        txtwidth.setInputType(InputType.TYPE_CLASS_TEXT);
        txtheight = (EditText) findViewById(R.id.height1);
        txtheight.setInputType(InputType.TYPE_CLASS_TEXT);
        txtlength = (EditText) findViewById(R.id.length1);
        txtlength.setInputType(InputType.TYPE_CLASS_TEXT);
      //  txtlength.setText(0);
        btnAdd1 = (Button) findViewById(R.id.productsizes);
        displayProducts = (TextView) findViewById(R.id.productsTxt);
        displayProducts.setText(selectdProName);

        /*sp1 = (Spinner) findViewById(R.id.productsspinner);*/

        btnAdd1.setOnClickListener(this);
    }
    public void onBackPressed() {
        //finishAffinity();
        Intent in = new Intent(AddProductSizes.this,Main2Activity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        // in.putExtra("PRODUCTID_KEY", pid);
        startActivity(in);
        // finish();
    }
    @Override
    public void onClick(View view) {
        if(view == btnAdd1){
            checkData();
            //for checking empty values
        }
    }

    private void checkData() {
        if (txtwidth.length() < 1 || txtheight.length() < 1 ) {
            Toast.makeText(AddProductSizes.this, "Fill All", Toast.LENGTH_SHORT).show();
        } else {
            uploadMultipart();
            Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
            txtwidth.setText("");
            txtheight.setText("");
            txtlength.setText("0");
           /* adapter.notifyDataSetChanged();

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
            AndroidNetworking.post(UPLOAD_URL)
                    .addBodyParameter("action","save")
                    .addBodyParameter("width", String.valueOf(wid))
                    .addBodyParameter("height",String.valueOf(heig))
                    .addBodyParameter("length",String.valueOf(length))
                    .addBodyParameter("productid", String.valueOf(selectdPId))

                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                    Toast.makeText(AddProductSizes.this, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();
                                    if (responseString.equalsIgnoreCase("Success")) {
                                      /*  //CLEAR EDITXTS
                                        txtwidth.setText("");
                                        txtheight.setText("");
                                        txtlength.setText("");*/
                                        /*adapter.notifyDataSetChanged();

                                       BackTask bt = new BackTask();
                                        bt.execute();*/

                                    }else
                                    {
                                        Toast.makeText(AddProductSizes.this, "PHP WASN'T SUCCESSFUL. ", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AddProductSizes.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }
                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(AddProductSizes.this, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
