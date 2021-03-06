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

public class DeleteGridProductSizes extends AppCompatActivity {

    private Spinner sp;
    private Button btnAdd;
    int click=0;
 int recivedProductId,recivedProductsizeId,recvdWidth,recvdHeight,recvdLength;
    String recvdProName,finalSelProSize;
    private ArrayAdapter<MySQLDataBase> adapter ;
    private static final String DATA_DELETE_URL=Config.productSizesGridsCRUD;
    private static final String Data_Spin = Config.productSizeImgUrlAddress;
    URL Data_Del_Spin = null;
    ArrayList<MySQLDataBase> mySQLDataBases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grid_product_sizes);
        Intent intent = getIntent();
        recivedProductId = intent.getExtras().getInt("PRODUCTID_KEY");
        recivedProductsizeId=intent.getExtras().getInt("PRODUCTSIZEID_KEY");
        recvdProName = intent.getExtras().getString("PRODUCTNAME_KEY");
        recvdWidth = intent.getExtras().getInt("PRODUCTSIZEWIDTH_KEY");
        recvdHeight = intent.getExtras().getInt("PRODUCTSIZEHEIGHT_KEY");
        recvdLength = intent.getExtras().getInt("PRODUCTSIZELENGTH_KEY");
        finalSelProSize = intent.getExtras().getString("FINALPROSELSIZE_KEY");
        mySQLDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductSizeList");
        if(recvdLength !=0 && recvdWidth !=0 && recvdHeight !=0){
            finalSelProSize =  recvdLength + " "+"X"+" " + recvdWidth + " "+"X"+" " + recvdHeight;

        }else if(recvdLength ==0 && recvdWidth !=0 && recvdHeight !=0){
            finalSelProSize =  recvdWidth + " "+"X"+" " + recvdHeight;

        }else if(recvdLength !=0 && recvdWidth ==0 && recvdHeight !=0){
            finalSelProSize =  recvdLength + " "+"X"+" "+ recvdHeight;

        }else if(recvdLength !=0 && recvdWidth !=0 && recvdHeight ==0 ){
            finalSelProSize =  recvdLength +" "+"X"+" " + recvdWidth ;

        }else if(recvdLength ==0 && recvdWidth !=0 && recvdHeight ==0 ){
            finalSelProSize = recvdWidth + "" ;

        }else if(recvdLength !=0 && recvdWidth ==0 && recvdHeight ==0 ){
            finalSelProSize = recvdLength + "" ;

        }else if(recvdLength ==0 && recvdWidth ==0 && recvdHeight !=0 ){
            finalSelProSize = recvdHeight + "" ;

        }
        Uri builtUri = Uri.parse(Data_Spin)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(recivedProductId))
                .appendQueryParameter(Config.PRODUCTSIZEID_PARAM,Integer.toString(recivedProductsizeId))
                .build();
        try {
            Data_Del_Spin = new URL(builtUri.toString());
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
                    click = click + 1;
                    if (click == 1) {
                        click = 0;

                        Intent in = new Intent(DeleteGridProductSizes.this, ProductSizeGridViewImages.class);
                        //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                       /* in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                        in.putExtra("PRODUCTID_KEY", recivedProductId);
                        in.putExtra("PRODUCTNAME_KEY",recvdProName );
                        in.putExtra("PRODUCTSIZEID_KEY", recivedProductsizeId);
                        in.putExtra("FINALPROSELSIZE_KEY", finalSelProSize);
                        in.putExtra("PRODUCTSIZEWIDTH_KEY", recvdWidth);
                        in.putExtra("PRODUCTSIZELENGTH_KEY", recvdLength);
                        in.putExtra("PRODUCTSIZEHEIGHT_KEY", recvdHeight);
                        in.putExtra("ProductSizeList",mySQLDataBases);
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

            Intent in = new Intent(DeleteGridProductSizes.this, ProductSizeGridViewImages.class);
            //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);
            in.putExtra("PRODUCTID_KEY", recivedProductId);
            in.putExtra("PRODUCTNAME_KEY",recvdProName );
            in.putExtra("PRODUCTSIZEID_KEY", recivedProductsizeId);
            in.putExtra("FINALPROSELSIZE_KEY", finalSelProSize);
            in.putExtra("PRODUCTSIZEWIDTH_KEY", recvdWidth);
            in.putExtra("PRODUCTSIZELENGTH_KEY", recvdLength);
            in.putExtra("PRODUCTSIZEHEIGHT_KEY", recvdHeight);
            in.putExtra("ProductSizeList",mySQLDataBases);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);*/
            finish();
            super.onBackPressed();
        }
    }
    private void initializeViews()
    {


        btnAdd= (Button) findViewById(R.id.deletegrid1);
        sp= (Spinner) findViewById(R.id.spgrid1);
        //sp.setPrompt("Select One....");
    }
    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents(final int productSizeImgId)
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
                    s.setProductSizeImageId(productSizeImgId);
                    if (s == null) {
                        Toast toast = Toast.makeText(DeleteGridProductSizes.this, "No Data To Delete", Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        //Toast.makeText(DeleteGridProductSizes.this, "No Data To Delete", Toast.LENGTH_SHORT).show();
                    } else {
                        AndroidNetworking.post(DATA_DELETE_URL)
                                .addBodyParameter("action", "delete")
                                .addBodyParameter("productsizeimageid", String.valueOf(s.getProductSizeImageId()))
                                .addBodyParameter("productname",recvdProName)
                                .addBodyParameter("productsize",finalSelProSize)
                                .setTag("TAG_ADD")
                                .build()
                                .getAsJSONArray(new JSONArrayRequestListener() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        if (response != null)
                                            try {
                                                //SHOW RESPONSE FROM SERVER
                                                String responseString = response.get(0).toString();
                                                Toast.makeText(DeleteGridProductSizes.this, " " + responseString, Toast.LENGTH_SHORT).show();
                                                if (responseString.equalsIgnoreCase("Successfully Deleted")) {
                                                   /* Intent intent = new Intent(DeleteGridProductSizes.this, DeleteGridProductSizes.class);
                                                    intent.putExtra("PRODUCTSIZEID_KEY", recivedProductsizeId);
                                                    intent.putExtra("PRODUCTID_KEY", recivedProductId);
                                                    intent.putExtra("PRODUCTNAME_KEY",recvdProName );
                                                    intent.putExtra("PRODUCTSIZEWIDTH_KEY", recvdWidth);
                                                    intent.putExtra("PRODUCTSIZELENGTH_KEY", recvdLength);
                                                    intent.putExtra("PRODUCTSIZEHEIGHT_KEY", recvdHeight);
                                                    startActivity(intent);
   *//* adapter.notifyDataSetChanged();
    BackTask bt = new BackTask();
    bt.execute();*/
                                                    AlertDialog.Builder alert = new AlertDialog.Builder(DeleteGridProductSizes.this);
                                                    alert.setTitle(Html.fromHtml("<font color='#ff0000'>Information!</font>"));
                                                    alert.setMessage("To Refresh Newly added content Go Back to Home Screen..\n Confirm Delete By Clicking on OK");
                                                    //alert.setMessage("Confirm Delete By Clicking on OK");
                                                    alert.setIcon(R.drawable.reload);
                                                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Intent in=new Intent(DeleteGridProductSizes.this,DeleteGridProductSizes.class);
                                                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                                                                    Intent.FLAG_ACTIVITY_NEW_TASK*/);
                                                            in.putExtra("PRODUCTID_KEY", recivedProductId);
                                                            in.putExtra("PRODUCTNAME_KEY",recvdProName );
                                                            in.putExtra("PRODUCTSIZEID_KEY", recivedProductsizeId);
                                                            in.putExtra("FINALPROSELSIZE_KEY", finalSelProSize);
                                                            in.putExtra("PRODUCTSIZEWIDTH_KEY", recvdWidth);
                                                            in.putExtra("PRODUCTSIZELENGTH_KEY", recvdLength);
                                                            in.putExtra("PRODUCTSIZEHEIGHT_KEY", recvdHeight);
                                                            in.putExtra("ProductSizeList",mySQLDataBases);
                                                            setResult(Activity.RESULT_OK,in);
                                                            startActivity(in);
                                                            finish();
                                                        }
                                                    });
                                                    alert.show();
                                                } else {
                                                    Toast.makeText(DeleteGridProductSizes.this, responseString, Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Toast.makeText(DeleteGridProductSizes.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                    }

                                    //ERROR
                                    @Override
                                    public void onError(ANError anError) {
                                        Toast.makeText(DeleteGridProductSizes.this, "UNSUCCESSFUL :  ERROR IS : " + anError.getMessage(), Toast.LENGTH_SHORT).show();
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
        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(String.valueOf(Data_Del_Spin));
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
                    int productSizeImageId = jo.getInt("ProductSizeImageId");
                    String imageName = jo.getString("Name");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setProductSizeImageId(productSizeImageId);
                    mySQLDataBase.setName(imageName);
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

            adapter=new ArrayAdapter(DeleteGridProductSizes.this,R.layout.spinner_layout, R.id.txt,listItems);
            sp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
                                           int position, long id) {
                    if(sp.getSelectedItem().toString().equals("Select One")){
                   /*     Toast.makeText(DeleteGridProductSizes.this,
                                "Your Selected : Nothing",
                                Toast.LENGTH_SHORT).show();*/
                    }else{
                    MySQLDataBase mySQLDataBase = (MySQLDataBase) mySQLDataBases.get(position-1);
                    final String name = mySQLDataBase.getName();
                    //  final int pid
                    final int proSizeImgId =mySQLDataBase.getProductSizeImageId() ;
                    handleClickEvents(proSizeImgId);
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
                    Toast.makeText(DeleteGridProductSizes.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }

            });


        }
    }
}
