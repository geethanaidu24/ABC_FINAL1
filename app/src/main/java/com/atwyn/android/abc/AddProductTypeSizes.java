package com.atwyn.android.abc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * Created by Atwyn on 5/5/2017.
 */

public class AddProductTypeSizes extends AppCompatActivity implements View.OnClickListener {

    private static final String DATA_INSERT_URL = Config.productTypeSizesCRUD;
    private EditText txtwidth, txtheight, txtlength;
    private TextView displaySelectedProductType,displaySelectedProduct;
    Context context;
    private Button btnAdd;
    int click=0;
    private static int selectedProdutId1, selectedProdutTypeId1;
    private static String selectedProductName1, selectedProductType1;
    ArrayList<MySQLDataBase> mySQLDataBases;
    ArrayList<MySQLDataBase> mySQLDataBases1;

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
            mySQLDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeSizeList");

            mySQLDataBases1 = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeList");
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
                            Intent intent = new Intent(AddProductTypeSizes.this, ProductTypeSizes.class);

                            // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                           /* intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                            intent.putExtra("PRODUCTID_KEY", selectedProdutId1);
                            intent.putExtra("PRODUCTNAME_KEY", selectedProductName1);
                            intent.putExtra("PRODUCTTYPEID_KEY", selectedProdutTypeId1);
                            intent.putExtra("PRODUCTTYPE_KEY", selectedProductType1);
                            intent.putExtra("ProductTypeSizeList",mySQLDataBases);
                            intent.putExtra("ProductTypeList",mySQLDataBases1);
                            setResult(Activity.RESULT_OK,intent);
                            startActivity(intent);*/
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
            btnAdd = (Button) findViewById(R.id.brandbtn);
            displaySelectedProductType =(TextView) findViewById(R.id.protypeDisplay);
            displaySelectedProduct =(TextView)findViewById(R.id.proDisplay);

            displaySelectedProduct.setText(selectedProductName1);
            displaySelectedProductType.setText(selectedProductType1);

          /*  sp1 = (Spinner) findViewById(R.id.productsspinner);
            sp2 = (Spinner) findViewById(R.id.spinner8);*/
            btnAdd.setOnClickListener(this);
        }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(AddProductTypeSizes.this, ProductTypeSizes.class);
            //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);
            in.putExtra("PRODUCTID_KEY", selectedProdutId1);
            in.putExtra("PRODUCTNAME_KEY", selectedProductName1);
            in.putExtra("PRODUCTTYPEID_KEY", selectedProdutTypeId1);
            in.putExtra("PRODUCTTYPE_KEY", selectedProductType1);
            in.putExtra("ProductTypeSizeList",mySQLDataBases);
            in.putExtra("ProductTypeList",mySQLDataBases1);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);*/
            finish();
        }
    }
    @Override
    public void onClick(View view) {
        click = click + 1;
        if (click == 1) {
            click = 0;
            if (view == btnAdd) {
                checkData();
                //for checking empty values
            }
        }
    }

    private void checkData() {
        if (txtwidth.length() < 1 || txtheight.length() < 1) {
            Toast toast = Toast.makeText(AddProductTypeSizes.this, "Fill All", Toast.LENGTH_SHORT);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
           // Toast.makeText(AddProductTypeSizes.this, "Fill All", Toast.LENGTH_SHORT).show();
        } else {
            uploadMultipart();
            Toast toast = Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
           // toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
           // Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
            txtwidth.setText("");
            txtheight.setText("");
            txtlength.setText("0");
            /*adapter.notifyDataSetChanged();
            adapter1.notifyDataSetChanged();
            BackTask bt = new BackTask();
            bt.execute();*/
            AlertDialog.Builder alert = new AlertDialog.Builder(AddProductTypeSizes.this);
            alert.setTitle(Html.fromHtml("<font color='#ff0000'>Information!</font>"));
            alert.setMessage("To Refresh Newly added content Go Back to Home Screen..");
            alert.setIcon(R.drawable.reload);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent in=new Intent(AddProductTypeSizes.this,AddProductTypeSizes.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                            Intent.FLAG_ACTIVITY_NEW_TASK*/);
                    in.putExtra("PRODUCTID_KEY", selectedProdutId1);
                    in.putExtra("PRODUCTNAME_KEY", selectedProductName1);
                    in.putExtra("PRODUCTTYPEID_KEY", selectedProdutTypeId1);
                    in.putExtra("PRODUCTTYPE_KEY", selectedProductType1);
                    in.putExtra("ProductTypeSizeList",mySQLDataBases);
                    in.putExtra("ProductTypeList",mySQLDataBases1);
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
                                    //Toast.makeText(AddProductTypeSizes.this, " " + responseString, Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(AddProductTypeSizes.this, " ", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AddProductTypeSizes.this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
