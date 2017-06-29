package com.example.admin.abc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.view.Gravity;
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
    int click=0;
    private static int selectdPId;
    private static String selectdProName;
   /* private ArrayAdapter<SizesDB> adapter;*/

   // public int ipid=0;
   ArrayList<MySQLDataBase> mySQLDataBases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_sizes);
   Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
       selectdPId= intent.getExtras().getInt("PRODUCTID_KEY");
       selectdProName = intent.getExtras().getString("PRODUCTNAME_KEY");
        mySQLDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductSizeList");
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

                        Intent in = new Intent(AddProductSizes.this, ProductSizes.class);
                        /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                        in.putExtra("PRODUCTID_KEY", selectdPId);
                        in.putExtra("PRODUCTNAME_KEY",selectdProName);
                        in.putExtra("ProductSizeList",mySQLDataBases);
                        setResult(Activity.RESULT_OK,in);
                        startActivity(in);*/
                        finish();
                    }
                }
            });

        }

        txtwidth = (EditText) findViewById(R.id.width1);
        txtwidth.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtheight = (EditText) findViewById(R.id.height1);
        txtheight.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtlength = (EditText) findViewById(R.id.length1);
        txtlength.setInputType(InputType.TYPE_CLASS_NUMBER);
      //  txtlength.setText(0);
        btnAdd1 = (Button) findViewById(R.id.productsizes);
        displayProducts = (TextView) findViewById(R.id.productsTxt);
        displayProducts.setText(selectdProName);

        /*sp1 = (Spinner) findViewById(R.id.productsspinner);*/

        btnAdd1.setOnClickListener(this);
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;

            Intent in = new Intent(AddProductSizes.this, ProductSizes.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*/);
            in.putExtra("PRODUCTID_KEY", selectdPId);
            in.putExtra("PRODUCTNAME_KEY",selectdProName);
            in.putExtra("ProductSizeList",mySQLDataBases);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);
            finish();
        }

    }
    @Override
    public void onClick(View view) {
        click = click + 1;
        if (click == 1) {
            click = 0;
            if (view == btnAdd1) {
                checkData();
                //for checking empty values
            }
        }
    }

    private void checkData() {
        if (txtwidth.length() < 1 || txtheight.length() < 1 ) {
            Toast toast = Toast.makeText(AddProductSizes.this, "Fill All", Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            //Toast.makeText(AddProductSizes.this, "Fill All", Toast.LENGTH_SHORT).show();
        } else {
            uploadMultipart();
            Toast toast = Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
            //toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
           // Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
            txtwidth.setText("");
            txtheight.setText("");
            txtlength.setText("0");
           /* adapter.notifyDataSetChanged();

            BackTask bt = new BackTask();
            bt.execute();*/
            AlertDialog.Builder alert = new AlertDialog.Builder(AddProductSizes.this);
            alert.setTitle(Html.fromHtml("<font color='#ff0000'>Information!</font>"));
            alert.setMessage("To Refresh Newly added content Go Back to Home Screen..");
            alert.setIcon(R.drawable.reload);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent in=new Intent(AddProductSizes.this,AddProductSizes.class);
                    in.putExtra("PRODUCTID_KEY", selectdPId);
                    in.putExtra("PRODUCTNAME_KEY",selectdProName);
                    in.putExtra("ProductSizeList",mySQLDataBases);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                            Intent.FLAG_ACTIVITY_NEW_TASK*/);
                    in.putExtra("PRODUCTID_KEY", selectdPId);
                    in.putExtra("PRODUCTNAME_KEY",selectdProName);
                    in.putExtra("ProductSizeList",mySQLDataBases);
                    setResult(Activity.RESULT_OK,in);
                    startActivity(in);
                    finish();
                }
            });
            alert.show();

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
                                 //   Toast.makeText(AddProductSizes.this, " " + responseString, Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(AddProductSizes.this, " ", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AddProductSizes.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
