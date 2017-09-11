package com.atwyn.android.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

import static com.atwyn.android.abc.R.id.productsadd;

//import static com.example.admin.abc.R.id.home;
//import static com.example.admin.abc.R.id.productsadd;

public class Products extends AppCompatActivity implements Serializable {

    final static String productsAddress = Config.productsUrlAddress;
    final static String productTypeUrlAddress = Config.productTypesUrlAddress;
    final static String productSizeUrl = Config.productSizesUrlAddress;
    private boolean loggedIn = false;
    private boolean checkNetworkConnection;
   // ArrayList<MySQLDataBase> mySQLProDataBases;
   ArrayList<MySQLDataBase> mySQLProTypeDataBases=new ArrayList<>();
    int click = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        final ListView lv = (ListView) findViewById(R.id.productLv);
        new ProductsDownloader(Products.this, productsAddress, lv).execute();
       /* Intent intent = getIntent();
        mySQLProDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductList");
        final ProductsListAdapter adapter=new ProductsListAdapter(this,mySQLProDataBases);
        lv.setAdapter(adapter);*/
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

                        Intent in = new Intent(Products.this, Main2Activity.class);
                        /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                        setResult(Activity.RESULT_OK,in);
                        startActivity(in);*/
                        finish();

                    }

                }
            });

         Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ho);
         toolbar.setOverflowIcon(drawable);

        }
    }




    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.


            SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        //getMenuInflater().inflate(R.menu.mainproducts, menu);

            if (loggedIn) {

               /* MenuItem item1 = menu.findItem(productsadd);
                item1.setVisible(true);
                MenuItem items = menu.findItem(R.id.productdelete);
                items.setVisible(true);
                MenuItem itemss = menu.findItem(R.id.logout);
                itemss.setVisible(true);
                MenuItem items2 = menu.findItem(R.id.h1);
                items2.setVisible(true);*/
                getMenuInflater().inflate(R.menu.mainproducts, menu);

            } else  {
               /* MenuItem item1 = menu.findItem(productsadd);
                item1.setVisible(false);
                MenuItem items = menu.findItem(R.id.productdelete);
                items.setVisible(false);
                MenuItem itemss = menu.findItem(R.id.logout);
                itemss.setVisible(false);
                MenuItem items2 = menu.findItem(R.id.h1);
                items2.setVisible(true);*/
                getMenuInflater().inflate(R.menu.home, menu);

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
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent in = new Intent(Products.this, AddProducts.class);
                // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(in);

                return true;
            }
        } else if (id == R.id.productdelete) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent inn = new Intent(Products.this, DeleteProducts.class);
               // inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
               /* inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);*/
                startActivity(inn);

                return true;
            }
        } else if (id == R.id.logout) {
            logout();
            return true;
        }else if(id==R.id.h1)
        {
            Intent inn = new Intent(Products.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
               /* inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);*/
            startActivity(inn);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
       //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(Products.this, Main2Activity.class);
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                   Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);*/
            finish();
            super.onBackPressed();

        }

    }

    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

       // alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setTitle(" Are you sure you want to logout?");
        alertDialogBuilder.setIcon(R.drawable.logoutt);
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
                        editor.apply();

                        //Starting login activity

                        Intent intent = new Intent(Products.this, Main2Activity.class);
                       intent.putExtra("finish",true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        finish();
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
    private class ProductsDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        String urlAddress;
        ListView lv;


        ProductsDownloader(Context c, String urlAddress, ListView lv) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.lv = lv;
            Log.d("result product url: ", "> " + urlAddress);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            return downloadData();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast toast = Toast.makeText(c, "Our products are Coming Soon! Thank you for your patience.", Toast.LENGTH_SHORT);

                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.setGravity(Gravity.FILL_HORIZONTAL,0,0);
                toast.show();
                //Toast.makeText(c, "Coming Soon...", Toast.LENGTH_SHORT).show();
            } else {

                //CALL DATA PARSER TO PARSE
                ProductsDataParser parser = new ProductsDataParser(c, lv, s);
                parser.execute();
            }
        }

        private String downloadData() {
            HttpURLConnection con = Connector.connect(urlAddress);
            if (con == null) {
                return null;
            }
            try {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder jsonData = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    jsonData.append(line).append("n");
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
    private class ProductsDataParser extends AsyncTask<Void,Void,Integer> {
        Context c;
        ListView lv;
        String jsonData;

        ArrayList<MySQLDataBase> mySQLDataBases=new ArrayList<>();
        ProductsDataParser(Context c, ListView lv, String jsonData) {
            this.c = c;
            this.lv = lv;
            this.jsonData = jsonData;
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
            {  click = click + 1;
                if (click == 1) {
                    click = 0;
                    Toast toast = Toast.makeText(c, "Our products are Coming Soon! Thank you for your patience.", Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    //Toast.makeText(c, "No Collection Available", Toast.LENGTH_SHORT).show();
                }
            }else
            {

                final ProductsListAdapter adapter=new ProductsListAdapter(c,mySQLDataBases);
                lv.setAdapter(adapter);
            }
        }

        private int parseData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for(int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);
                    int ProductId=jo.getInt("ProductId");
                    String ProductName =jo.getString("ProductName");
                    String ImageUrl=jo.getString("ImageUrl");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setProductId(ProductId);
                    mySQLDataBase.setProductName(ProductName);
                    mySQLDataBase.setProductImageUrl(ImageUrl);
                    mySQLDataBases.add(mySQLDataBase);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }

    }
    private class ProductsListAdapter extends BaseAdapter {

        Context c;
        ArrayList<MySQLDataBase> mySQLDataBases;
        LayoutInflater inflater;

        private ProductsListAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return mySQLDataBases.size();
        }
        @Override
        public Object getItem(int position) {
            return mySQLDataBases.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.productimage_list_view, parent,false);
            }
            TextView nametxt= (TextView) convertView.findViewById(R.id.textViewURL);
            ImageView img= (ImageView) convertView.findViewById(R.id.imageDownloaded);
            //BIND DATA
            MySQLDataBase mySQLDataBase=(MySQLDataBase) this.getItem(position);
            final String name = mySQLDataBase.getProductName();
            final String url = mySQLDataBase.getProductImageUrl();
            final int pid = mySQLDataBase.getProductId();
            final String finalUrl=Config.mainUrlAddress + url;
            nametxt.setText(mySQLDataBase.getProductName());

            //IMG
            PicassoClient.downloadImage(c,finalUrl,img);

            // testing new activity condition
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        openNextActivity(pid, name);
                    }
                }
            });

            return convertView;
        }
        private void openNextActivity(int recivedpid, String recivedname){
            Uri builtUri = Uri.parse(productTypeUrlAddress)
                    .buildUpon()
                    .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(recivedpid))
                    .build();
            URL ProTypeurlAddress = null;
            try {
                ProTypeurlAddress = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            new ProductTypesDownloader(Products.this,ProTypeurlAddress,recivedpid,recivedname).execute();
        }
    }
    private class ProductTypesDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        URL finalProducturlAddress;
        int localpid;
        String localname;

        ProductTypesDownloader(Context c, URL urlAddress, int recivedpid, String recivedname) {
            this.c = c;
            this.finalProducturlAddress = urlAddress;
            this.localpid = recivedpid;
            this.localname = recivedname;

            Log.d("newActivity url: ", "> " + urlAddress);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            return downloadTypeData();


        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s==null)
            {
                click = click + 1;
                if (click == 1) {
                    click = 0;
                    Toast.makeText(c, "Our products are Coming Soon! Thank you for your patience.", Toast.LENGTH_SHORT).show();
                }
            }else {
                //CALL DATA PARSER TO PARSE
                ProductTypesDataParser parser=new ProductTypesDataParser(c,s,localpid,localname);
                parser.execute();
            }
        }
        private String downloadTypeData() {
            HttpURLConnection con = Connector.connect(String.valueOf(finalProducturlAddress));
            if (con == null) {
                return null;
            }
            try {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder jsonData = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    /*jsonData.append(line + "n");*/
                    jsonData.append(line).append("n");
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
    private class ProductTypesDataParser extends AsyncTask<Void,Void,Integer> {
        Context c;
        String jsonData;
        int finalpid;
        String finalname;
        /*ArrayList<MySQLDataBase> mySQLDataBases=new ArrayList<>();*/

        private ProductTypesDataParser(Context c, String jsonData, int pid, String name) {
            this.c = c;
            this.jsonData = jsonData;
            this.finalpid = pid;
            this.finalname = name;

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
                openAnotherActivityCondition(finalpid,finalname);

            }else {
                click = click + 1;
                if (click == 1) {
                    click = 0;
                    Intent intent = new Intent(c, ProductTypes.class);
                   // Bundle ProductTypeInfo = new Bundle();
                   // ProductTypeInfo.putSerializable("ProductTypeList", mySQLProTypeDataBases);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("PRODUCTID_KEY", finalpid);
                    intent.putExtra("PRODUCTNAME_KEY", finalname);
                    intent.putExtra("ProductTypeList",mySQLProTypeDataBases);
                    c.startActivity(intent);
                }
            }
        }
       private void openAnotherActivityCondition(int fpid, String fname){
           Uri builtUri = Uri.parse(productSizeUrl)
                   .buildUpon()
                   .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(fpid))
                   .build();
           URL ProSizeurlAddress = null;
           try {
               ProSizeurlAddress = new URL(builtUri.toString());
           } catch (MalformedURLException e) {
               e.printStackTrace();
           }

           new ProductSizesDownloader(Products.this, ProSizeurlAddress, fpid,fname).execute();

       }
        private int parseData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                mySQLProTypeDataBases.clear();
                MySQLDataBase mySQLProTypeDataBase;
                for(int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);
                    int ProductTypeId=jo.getInt("ProductTypeId");
                    String ProductType =jo.getString("ProductType");
                    String ImageUrl=jo.getString("ImageUrl");
                    int ProductId = jo.getInt("ProductId");
                    mySQLProTypeDataBase=new MySQLDataBase();
                    mySQLProTypeDataBase.setProductTypeId(ProductTypeId);
                    mySQLProTypeDataBase.setProductType(ProductType);
                    mySQLProTypeDataBase.setProductTypeImageUrl(ImageUrl);
                    mySQLProTypeDataBase.setProductId(ProductId);
                    mySQLProTypeDataBases.add(mySQLProTypeDataBase);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }

    }
    private class ProductSizesDownloader extends AsyncTask<Void, Void, String> {
        Context c;
        URL sizeurlAddress;
        int pid;
        String pname;
        private ProductSizesDownloader(Context c, URL urlAddress,int pid,String pname ) {
            this.c = c;
            this.sizeurlAddress = urlAddress;
            this.pid = pid;
            this.pname=pname;
            Log.d("newActivity url: ", "> " + sizeurlAddress);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            return downloadTypeData();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast toast = Toast.makeText(c, "Our products are Coming Soon! Thank you for your patience.", Toast.LENGTH_SHORT);

                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
               // Toast.makeText(c, "Coming Soon...", Toast.LENGTH_SHORT).show();
            } else {
                //CALL DATA PARSER TO PARSE
                ProductSizesDataParser parser = new ProductSizesDataParser(c,s, pid,pname);
                parser.execute();
            }
        }

        private String downloadTypeData() {
            HttpURLConnection con = Connector.connect(String.valueOf(sizeurlAddress));
            if (con == null) {
                return null;
            }
            try {
                InputStream sizesReader = new BufferedInputStream(con.getInputStream());
                BufferedReader sizeBufferReader = new BufferedReader(new InputStreamReader(sizesReader));
                String line;
                StringBuilder jsonData = new StringBuilder();
                while ((line = sizeBufferReader.readLine()) != null) {
                    jsonData.append(line).append("n");
                }
                sizeBufferReader.close();
                sizesReader.close();
                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class ProductSizesDataParser extends AsyncTask<Void, Void, Integer> {
        Context c;
        String jsonData;
        int pid;
        String pname;
        ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();

        private ProductSizesDataParser(Context c, String jsonData, int pid, String pname) {
            this.c = c;
            this.jsonData = jsonData;
            this.pid = pid;
            this.pname=pname;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            return this.parseSizesData();
        }

        @Override

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == 0) {
                if(loggedIn) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;

                        Intent in = new Intent(Products.this, Trial.class);
                       in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        in.putExtra("PRODUCTID_KEY", pid);
                        in.putExtra("PRODUCTNAME_KEY", pname);
                        in.putExtra("ProductTypeList", mySQLProTypeDataBases);
                        in.putExtra("ProductSizeList", mySQLDataBases);
                        startActivity(in);
                    }
                }else{
                    click = click + 1;
                    if (click == 1) {
                        click = 0;

                        Toast.makeText(c, "Our products are Coming Soon! Thank you for your patience.", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent intent = new Intent(c, ProductSizes.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("PRODUCTID_KEY", pid);
                    intent.putExtra("PRODUCTNAME_KEY", pname);
                    intent.putExtra("ProductSizeList", mySQLDataBases);
                    c.startActivity(intent);
                }
            }
        }

        private int parseSizesData() {
            try {
                JSONArray sizeArray = new JSONArray(jsonData);
                JSONObject sizeObject = null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < sizeArray.length(); i++) {
                    sizeObject = sizeArray.getJSONObject(i);
                    Log.d("result response: ", "> " + sizeObject);
                    int ProductSizeId = sizeObject.getInt("ProductSizeId");
                    int Width = sizeObject.getInt("Width");
                    int Height = sizeObject.getInt("Height");
                    int Length = sizeObject.getInt("Length");

                    // String Measure =jo.getString("Measurement");
                    int ProductTypeId = sizeObject.optInt("ProductTypeId", 0);
                    // int ProductTypeId=jo.getInt("ProductTypeId");
                    int ProductId = sizeObject.getInt("ProductId");
                    mySQLDataBase = new MySQLDataBase();

                    mySQLDataBase.setProductSizeId(ProductSizeId);
                    mySQLDataBase.setWidth(Width);
                    mySQLDataBase.setHeight(Height);
                    mySQLDataBase.setLength(Length);

                    //productTypeSizeDBData.setMeasurement(Measure);
                    mySQLDataBase.setProductTypeId(ProductTypeId);
                    mySQLDataBase.setProductId(ProductId);
                    mySQLDataBases.add(mySQLDataBase);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    /*public static void disableTouchTheft(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }*/

}
