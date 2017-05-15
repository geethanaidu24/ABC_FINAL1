package com.example.admin.abc;

/**
 * Created by Geetha on 4/10/2017 for opening Product types activity based on user clicked product .
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProductTypes extends AppCompatActivity implements Serializable {
    ImageView back;
    Context c;
    private boolean loggedIn = false;
    final static String productSubTypeCheckUrl = Config.productSubTypesUrlAddress;
    final static String productSizeCheckUrl = Config.productTypeSizesUrlAddress;
    private static int selectedPid;
    private static String selectedPname;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types);
        LinearLayout ll = (LinearLayout) findViewById(R.id.products_type);
        final ListView lv = (ListView) findViewById(R.id.productTypesLv);
        TextView typeNameTxt = (TextView) findViewById(R.id.SelProductName);
        Intent intent = getIntent();
        selectedPname = intent.getExtras().getString("PRODUCTNAME_KEY");
        selectedPid = intent.getExtras().getInt("PRODUCTID_KEY");

        ArrayList<ProductTypesDB> productTypesDBs = (ArrayList<ProductTypesDB>) intent.getSerializableExtra("ProductTypeList");

        typeNameTxt.setText(selectedPname);
        Log.d("result response: ", "> " + productTypesDBs);

        final ProductTypesListAdapter adapter = new ProductTypesListAdapter(this, productTypesDBs, selectedPid, selectedPname);
        lv.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductTypes.this, Products.class);
                    //startActivity(in);
                    finish();
                }
            });


            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
            toolbar.setOverflowIcon(drawable);

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, true);
        getMenuInflater().inflate(R.menu.mainproducts, menu);
        if (loggedIn == true) {
            MenuItem item = menu.findItem(R.id.productsadd);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.productdelete);
            items.setVisible(true);
            MenuItem itemss = menu.findItem(R.id.logout);
            items.setVisible(true);

        } else if (loggedIn == false) {
            return false;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.productsadd) {
            Intent in = new Intent(ProductTypes.this, AddProductsTypes.class);
            in.putExtra("PRODUCTID_KEY",selectedPid);
            in.putExtra("PRODUCTNAME_KEY",selectedPname);
            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(ProductTypes.this, DeleteProductTypes.class);
            startActivity(inn);

            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ProductTypesListAdapter extends BaseAdapter {

        Context c;
        ArrayList<ProductTypesDB> productTypesDBs;
        LayoutInflater inflater;
        String FinalPname;
        int FinalPid;
        private ProductTypesListAdapter(Context c, ArrayList<ProductTypesDB> productTypesDBs,int selectdPid, String selectdPname) {
            this.c = c;
            this.productTypesDBs = productTypesDBs;
            this.FinalPname = selectdPname;
            this.FinalPid=selectdPid;
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return productTypesDBs.size();
        }
        @Override
        public Object getItem(int position) {
            return productTypesDBs.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.producttypeimage_list_view,parent,false);
            }
            TextView typeNameTypeTxt= (TextView) convertView.findViewById(R.id.textViewURL1);
            ImageView img= (ImageView) convertView.findViewById(R.id.imageTypePro);

            //BIND DATA
            ProductTypesDB productTypesDB=(ProductTypesDB) this.getItem(position);
            final int ProductTypeId = productTypesDB.getProductTypeId();
            final String ProductTypeName = productTypesDB.getProductType();
            final int ProductId = productTypesDB.getProductId();
            final String imageUrl = productTypesDB.getImageUrl();
            final String finalUrl=Config.mainUrlAddress + imageUrl;

            typeNameTypeTxt.setText(productTypesDB.getProductType());

            //IMG
            PicassoClient.downloadImage(c,finalUrl,img);

            // open new activity
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    openActivityCondition(FinalPid,FinalPname,ProductTypeId,ProductTypeName);
                }
            });

            return convertView;
        }
        public void openActivityCondition(int recivedPid, String recivedPname, int recivedPTid, String recivedPTname){
            final String ConditionUrlAddress = productSubTypeCheckUrl + recivedPTid;
            new ProductSubTypesDownloader(ProductTypes.this, ConditionUrlAddress,recivedPid,recivedPname, recivedPTid, recivedPTname).execute();

        }
    }
    private class ProductSubTypesDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        String finalUrlAddress;
        int selectedProductId,selectedProductTypeId;
        String selectedProductType,selectedProduct;
        private ProductSubTypesDownloader(Context c, String ConditionUrlAddress, int recivedPid, String recivedPname,int recivedPTid, String recivedPTname) {
            this.c = c;
            this.finalUrlAddress = ConditionUrlAddress;
            this.selectedProductId=recivedPid;
            this.selectedProduct=recivedPname;
            this.selectedProductTypeId = recivedPTid;
            this.selectedProductType = recivedPTname;
            Log.d("newActivity url: ", "> " + ConditionUrlAddress);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String data = downloadSubTypeData();
            return data;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
            } else {
                //CALL DATA PARSER TO PARSE
                ProductSubTypesDataParser parser = new ProductSubTypesDataParser(c,s,selectedProductId,selectedProduct,selectedProductTypeId,selectedProductType);
                parser.execute();
            }
        }

        private String downloadSubTypeData() {
            HttpURLConnection con = Connector.connect(finalUrlAddress);
            if (con == null) {
                return null;
            }
            try {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer jsonData = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    jsonData.append(line + "n");
                }
                br.close();
                is.close();
                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    private class ProductSubTypesDataParser extends AsyncTask<Void, Void, Integer> {
        Context c;
        String jsonData;
        int finalProductId,finalProductTypeId;
        String finalProductType,finalProductName;
        ArrayList<ProductSubTypesDB> productSubTypesDBs = new ArrayList<>();

        private ProductSubTypesDataParser(Context c, String jsonData, int pid,String pname,int ptid, String ptname) {
            this.c = c;
            this.jsonData = jsonData;
            this.finalProductTypeId = ptid;
            this.finalProductType = ptname;
            this.finalProductId=pid;
            this.finalProductName=pname;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            return this.parseSubTypeData();
        }

        @Override

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if(result==0)
            {

                openAnotherActivityCondition(finalProductId,finalProductName,finalProductTypeId,finalProductType);

            }else
            {

                Intent intent = new Intent(c,ProductSubTypes.class);
                intent.putExtra("PRODUCTTYPEID_KEY", finalProductTypeId);
                intent.putExtra("PRODUCTTYPE_KEY",finalProductType);
                intent.putExtra("ProductSubTypeList",productSubTypesDBs);
                c.startActivity(intent);
            }
        }

        private void openAnotherActivityCondition(int finalProductId, String finalProductName,int finalProductTypeId, String finalProductType) {

            Uri builtUri = Uri.parse(productSizeCheckUrl)
                    .buildUpon()
                    .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(finalProductId))
                    .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(finalProductTypeId))
                    .build();
            URL urlAddress = null;
            try {
                urlAddress = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            new ProductTypeSizesDownloader(ProductTypes.this,urlAddress,finalProductId,finalProductName,finalProductTypeId,finalProductType).execute();

        }
        private int parseSubTypeData() {
            try {
                JSONArray ja = new JSONArray(jsonData);
                JSONObject jo = null;
                productSubTypesDBs.clear();
                ProductSubTypesDB productSubTypesDB;
                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);
                    int ProductSubTypeId = jo.getInt("ProductSubTypeId");
                    String ProductSubTypeName = jo.getString("ProductSubTypeName");
                    String ImageUrl = jo.getString("ImageUrl");
                    int ProductTypeId = jo.getInt("ProductTypeId");
                    productSubTypesDB = new ProductSubTypesDB();
                    productSubTypesDB.setProductSubTypeId(ProductSubTypeId);
                    productSubTypesDB.setProductSubTypeName(ProductSubTypeName);
                    productSubTypesDB.setImageUrl(ImageUrl);
                    productSubTypesDB.setProductTypeId(ProductTypeId);
                    productSubTypesDBs.add(productSubTypesDB);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }

    }
    private class ProductTypeSizesDownloader extends AsyncTask<Void, Void, String> {
        Context c;
        URL urlAddress;
        int recivedProductId, recivedProductTypeId;
        String recivedProductName,recivedProductType;
        private ProductTypeSizesDownloader(Context c, URL urlAddress,int recvdProId,String recvdProName,int recvdProTypId,String recvdProType) {
            this.c = c;
            this.urlAddress = urlAddress;
           this.recivedProductId = recvdProId;
            this.recivedProductTypeId = recvdProTypId;
            this.recivedProductName=recvdProName;
            this.recivedProductType=recvdProType;
            Log.d("newActivity url: ", "> " + urlAddress);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String data = downloadTypeSizesData();
            return data;

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s==null)
            {
                Toast.makeText(c,"Unsuccessful,Null returned",Toast.LENGTH_SHORT).show();
            }else {
                //CALL DATA PARSER TO PARSE
                ProductTypeSizesDataParser parser=new ProductTypeSizesDataParser(c,s,recivedProductId,recivedProductName,recivedProductTypeId,recivedProductType);
                parser.execute();
            }
        }
        private String downloadTypeSizesData() {
            HttpURLConnection con = Connector.connect(String.valueOf(urlAddress));
            if (con == null) {
                return null;
            }
            try {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer jsonData = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    jsonData.append(line + "n");
                }
                br.close();
                is.close();
                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private class ProductTypeSizesDataParser extends AsyncTask<Void,Void,Integer> {
        Context c;
        String jsonData;
        int finalProId, finalProTypeId;
        String finalProName,finalProType;

        ArrayList<ProductTypeSizeDBData> productTypeSizeDBDatas = new ArrayList<>();

        private ProductTypeSizesDataParser(Context c, String jsonData, int pid,String pname, int ptid,String ptname) {
            this.c = c;
            this.jsonData = jsonData;
            this.finalProId =pid;
            this.finalProTypeId=ptid;
            this.finalProName=pname;
            this.finalProType=ptname;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(Void... params) {
            return this.parseData();
        }
        @Override

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if(result==0)
            {
                Intent intent = new Intent(c,ProductTypesGridView.class);
                intent.putExtra("PRODUCTID_KEY",finalProId);
                intent.putExtra("PRODUCTTYPEID_KEY",finalProTypeId);
               // intent.putExtra("ProductTypeSizeList",productTypeSizeDBDatas);
                c.startActivity(intent);

            }else
            {
                Intent intent = new Intent(c,ProductTypeSizes.class);
                intent.putExtra("PRODUCTID_KEY",finalProId);
                intent.putExtra("PRODUCTNAME_KEY",finalProName);
                intent.putExtra("PRODUCTTYPEID_KEY",finalProTypeId);
                intent.putExtra("PRODUCTTYPE_KEY",finalProType);
                intent.putExtra("ProductTypeSizeList",productTypeSizeDBDatas);
                c.startActivity(intent);
            }
        }
        private int parseData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                productTypeSizeDBDatas.clear();
                ProductTypeSizeDBData productTypeSizeDBData;
                for(int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);
                    int ProductSizeId=jo.getInt("ProductSizeId");
                    int Width = jo.getInt("Width");
                    int Height = jo.getInt("Height");
                    int Length =jo.getInt("Length");

                    // String Measure =jo.getString("Measurement");
                    int ProductTypeId=jo.getInt("ProductTypeId");
                    int ProductId = jo.getInt("ProductId");
                    productTypeSizeDBData=new ProductTypeSizeDBData();

                    productTypeSizeDBData.setProductSizeId(ProductSizeId);
                    productTypeSizeDBData.setWidth(Width);
                    productTypeSizeDBData.setHeight(Height);
                    productTypeSizeDBData.setLength(Length);

                    //productTypeSizeDBData.setMeasurement(Measure);
                    productTypeSizeDBData.setProductTypeId(ProductTypeId);
                    productTypeSizeDBData.setProductId(ProductId);
                    productTypeSizeDBDatas.add(productTypeSizeDBData);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }


    }
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.KEY_USER, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(ProductTypes.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}


