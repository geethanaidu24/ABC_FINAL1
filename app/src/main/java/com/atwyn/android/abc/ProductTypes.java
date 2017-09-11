package com.atwyn.android.abc;

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

//import static com.example.admin.abc.R.id.productsadd;

public class ProductTypes extends AppCompatActivity implements Serializable {
    ImageView back;
    Context c;
    int click=0;
    private boolean loggedIn = false;
    final static String productSubTypeCheckUrl = Config.productSubTypesUrlAddress;
    final static String productSizeCheckUrl = Config.productTypeSizesUrlAddress;
    final static String productTypesGridurl = Config.productTypeImgUrlAddress;
    private static int selectedPid;
    private static String selectedPname;
    ArrayList<MySQLDataBase> mySQLDataBases1;
    ArrayList<MySQLDataBase> mySQLProSubDataBases = new ArrayList<>();
    ArrayList<MySQLDataBase> mySQLProTypeSizeDataBases = new ArrayList<>();
       @Override
    public void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types);
        LinearLayout ll = (LinearLayout) findViewById(R.id.products_type);
        final ListView lv = (ListView) findViewById(R.id.productTypesLv);
        TextView typeNameTxt = (TextView) findViewById(R.id.SelProductName);
        Intent intent = getIntent();
       // Bundle bundle = getIntent().getBundleExtra("ProductTypeList");
        selectedPname = intent.getExtras().getString("PRODUCTNAME_KEY");
        selectedPid = intent.getExtras().getInt("PRODUCTID_KEY");
        mySQLDataBases1 = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeList");
      //  mySQLDataBases1 = (ArrayList<MySQLDataBase>) bundle.getSerializable("ProductTypeList");
        typeNameTxt.setText(selectedPname);
        Log.d("result response: ", "> " + mySQLDataBases1);

        final ProductTypesListAdapter adapter = new ProductTypesListAdapter(this, mySQLDataBases1, selectedPid, selectedPname);
        lv.setAdapter(adapter);

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

                      // ProductTypes.super.onBackPressed();
                        Intent in = new Intent(ProductTypes.this, Products.class);
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
           /* MenuItem item = menu.findItem(R.id.productsadd);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.productdelete);
            items.setVisible(true);
            MenuItem itemss = menu.findItem(R.id.logout);
            items.setVisible(true);
            MenuItem items2 = menu.findItem(R.id.h1);
            items2.setVisible(true);
*/
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

                Intent in = new Intent(ProductTypes.this, AddProductsTypes.class);
              //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                in.putExtra("PRODUCTID_KEY", selectedPid);
                in.putExtra("PRODUCTNAME_KEY", selectedPname);
                in.putExtra("ProductTypeList", mySQLDataBases1);
                startActivity(in);
                //startActivityForResult(in);
                return true;
            }
        } else if (id == R.id.productdelete) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent inn = new Intent(ProductTypes.this, DeleteProductTypes.class);
               // inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
               inn.putExtra("PRODUCTID_KEY", selectedPid);
                inn.putExtra("PRODUCT_NAME",selectedPname);
                inn.putExtra("ProductTypeList", mySQLDataBases1);
                startActivity(inn);
                return true;
            }
        } else if (id == R.id.logout) {
            logout();
            return true;
        }
        else if(id==R.id.h1)
        {
            Intent inn = new Intent(ProductTypes.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
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
            Intent in = new Intent(ProductTypes.this, Products.class);
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);*/
           // setResult(Activity.RESULT_OK,in);
           // startActivity(in);
            finish();
            super.onBackPressed();

        }

    }

    private class ProductTypesListAdapter extends BaseAdapter {

        Context c;
        ArrayList<MySQLDataBase> mySQLDataBases;
        LayoutInflater inflater;
        String FinalPname;
        int FinalPid;
        private ProductTypesListAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases,int selectdPid, String selectdPname) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
            this.FinalPname = selectdPname;
            this.FinalPid=selectdPid;
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
                convertView=inflater.inflate(R.layout.producttypeimage_list_view,parent,false);
            }
            TextView typeNameTypeTxt= (TextView) convertView.findViewById(R.id.textViewURL1);
            ImageView img= (ImageView) convertView.findViewById(R.id.imageTypePro);

            //BIND DATA
            MySQLDataBase mySQLDataBase=(MySQLDataBase) this.getItem(position);
            final int ProductTypeId = mySQLDataBase.getProductTypeId();
            final String ProductTypeName = mySQLDataBase.getProductType();
            final int ProductId = mySQLDataBase.getProductId();
            final String imageUrl = mySQLDataBase.getProductTypeImageUrl();
            final String finalUrl=Config.mainUrlAddress + imageUrl;

            typeNameTypeTxt.setText(mySQLDataBase.getProductType());

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
        private void openActivityCondition(int recivedPid, String recivedPname, int recivedPTid, String recivedPTname){
            Uri builtUri = Uri.parse(productSubTypeCheckUrl)
                    .buildUpon()
                    .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(recivedPTid))
                    .build();
            URL ProSubTypeurlAddress = null;
            try {
                ProSubTypeurlAddress = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            new ProductSubTypesDownloader(ProductTypes.this, ProSubTypeurlAddress,recivedPid,recivedPname, recivedPTid, recivedPTname).execute();

        }
    }
    private class ProductSubTypesDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        URL finalSubTypeUrlAddress;
        int selectedProductId,selectedProductTypeId;
        String selectedProductType,selectedProduct;
        private ProductSubTypesDownloader(Context c, URL ConditionUrlAddress, int recivedPid, String recivedPname, int recivedPTid, String recivedPTname) {
            this.c = c;
            this.finalSubTypeUrlAddress = ConditionUrlAddress;
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
            return downloadSubTypeData();


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast.makeText(c, "Our products are Coming Soon! Thank you for your patience.", Toast.LENGTH_SHORT).show();
            } else {
                //CALL DATA PARSER TO PARSE
                ProductSubTypesDataParser parser = new ProductSubTypesDataParser(c,s,selectedProductId,selectedProduct,selectedProductTypeId,selectedProductType);
                parser.execute();
            }
        }

        private String downloadSubTypeData() {
            HttpURLConnection con = Connector.connect(String.valueOf(finalSubTypeUrlAddress));
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


    private class ProductSubTypesDataParser extends AsyncTask<Void, Void, Integer> {
        Context c;
        String jsonData;
        int finalProductId,finalProductTypeId;
        String finalProductType,finalProductName;
       /* ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();*/

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

            }else {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent intent = new Intent(c, ProductSubTypes.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("PRODUCTTYPEID_KEY", finalProductTypeId);
                    intent.putExtra("PRODUCTTYPE_KEY", finalProductType);
                    intent.putExtra("PRODUCTID_KEY", selectedPid);
                    intent.putExtra("PRODUCTNAME_KEY", selectedPname);
                    intent.putExtra("ProductSubTypeList", mySQLProSubDataBases);
                    intent.putExtra("ProductTypeList",mySQLDataBases1);
                    c.startActivity(intent);
                }
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
                mySQLProSubDataBases.clear();
                MySQLDataBase mySQLProSubDataBase;
                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);
                    int ProductSubTypeId = jo.getInt("ProductSubTypeId");
                    String ProductSubTypeName = jo.getString("ProductSubTypeName");
                    String ImageUrl = jo.getString("ImageUrl");
                    int ProductTypeId = jo.getInt("ProductTypeId");
                    mySQLProSubDataBase = new MySQLDataBase();
                    mySQLProSubDataBase.setProductSubTypeId(ProductSubTypeId);
                    mySQLProSubDataBase.setProductSubTypeName(ProductSubTypeName);
                    mySQLProSubDataBase.setProductSubTypeImageUrl(ImageUrl);
                    mySQLProSubDataBase.setProductTypeId(ProductTypeId);
                    mySQLProSubDataBases.add(mySQLProSubDataBase);
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
            return downloadTypeSizesData();


        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s==null)
            {
                Toast.makeText(c,"Our products are Coming Soon! Thank you for your patience.",Toast.LENGTH_SHORT).show();
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
    private class ProductTypeSizesDataParser extends AsyncTask<Void,Void,Integer> {
        Context c;
        String jsonData;
        int finalProId, finalProTypeId;
        String finalProName,finalProType;

        /*ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();*/

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
                openAnotherActivityCondition(finalProId,finalProName,finalProTypeId,finalProType);

            }else {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent intent = new Intent(c, ProductTypeSizes.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("PRODUCTID_KEY", finalProId);
                    intent.putExtra("PRODUCTNAME_KEY", finalProName);
                    intent.putExtra("PRODUCTTYPEID_KEY", finalProTypeId);
                    intent.putExtra("PRODUCTTYPE_KEY", finalProType);
                    intent.putExtra("ProductTypeSizeList", mySQLProTypeSizeDataBases);
                    intent.putExtra("ProductTypeList",mySQLDataBases1);
                    c.startActivity(intent);
                }
            }
        }
        private void openAnotherActivityCondition(int proid,String proname,int protyid,String protyname){
            Uri builtUri = Uri.parse(productTypesGridurl)
                    .buildUpon()
                    .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(proid))
                    .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(protyid))
                    .build();
            URL urlAddress = null;
            try {
                urlAddress = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            new ProductTypeImagesDownloader(ProductTypes.this,urlAddress,proid,proname,protyid,protyname).execute();
        }
        private int parseData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                mySQLProTypeSizeDataBases.clear();
                MySQLDataBase mySQLProTypeSizeDataBase;
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
                    mySQLProTypeSizeDataBase=new MySQLDataBase();

                    mySQLProTypeSizeDataBase.setProductSizeId(ProductSizeId);
                    mySQLProTypeSizeDataBase.setWidth(Width);
                    mySQLProTypeSizeDataBase.setHeight(Height);
                    mySQLProTypeSizeDataBase.setLength(Length);

                    //productTypeSizeDBData.setMeasurement(Measure);
                    mySQLProTypeSizeDataBase.setProductTypeId(ProductTypeId);
                    mySQLProTypeSizeDataBase.setProductId(ProductId);
                    mySQLProTypeSizeDataBases.add(mySQLProTypeSizeDataBase);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }


    }
    private class ProductTypeImagesDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        URL urlAddress;
        int productid;
        int producttypeid;
        String productname,producttypename;
        private ProductTypeImagesDownloader(Context c, URL urlAddress, int pid,String pname, int ptid,String ptname) {
            this.c = c;
            this.urlAddress = urlAddress;
           this.productid=pid;
            this.productname=pname;
            this.producttypeid=ptid;
            this.producttypename = ptname;
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
            { Toast toast = Toast.makeText(c,"Our products are Coming Soon! Thank you for your patience.",Toast.LENGTH_SHORT);

                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
                //Toast.makeText(c,"Coming Soon...",Toast.LENGTH_SHORT).show();
            }else {
                //CALL DATA PARSER TO PARSE
                ProductTypeImagesDataParser parser=new ProductTypeImagesDataParser(c, s,productid,productname,producttypeid,producttypename);
                parser.execute();
            }
        }
        private String downloadTypeData() {
            HttpURLConnection con = Connector.connect(String.valueOf(urlAddress));
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
    private class ProductTypeImagesDataParser extends AsyncTask<Void,Void,Integer> {
        Context c;
        String jsonData;
        int pid,ptid;
        String pname,ptname;
        ArrayList<MySQLDataBase> mySQLDataBases=new ArrayList<>();

        private ProductTypeImagesDataParser(Context c, String jsonData, int pid, String pname,int ptid,String ptname) {
            this.c = c;

            this.jsonData = jsonData;
            this.pid=pid;
            this.ptid=ptid;
            this.pname=pname;
            this.ptname=ptname;
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
                if(loggedIn) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;

                        Intent in = new Intent(ProductTypes.this, Trial1.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        in.putExtra("PRODUCTID_KEY", pid);
                        in.putExtra("PRODUCTNAME_KEY", pname);
                        in.putExtra("PRODUCTTYPEID_KEY", ptid);
                        in.putExtra("PRODUCTTYPE_KEY", ptname);
                        in.putExtra("ProductTypeList",mySQLDataBases1);
                        in.putExtra("ProductSubTypeList", mySQLProSubDataBases);
                        in.putExtra("ProductTypeSizeList", mySQLProTypeSizeDataBases);
                        in.putExtra("ProductTypeGridList", mySQLDataBases);
                        startActivity(in);
                    }
                }else {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        Toast toast = Toast.makeText(c, "Our products are Coming Soon! Thank you for your patience.", Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                       // Toast.makeText(c, "No Collection Available", Toast.LENGTH_SHORT).show();
                    }
                }
            }else {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent intent = new Intent(c, ProductTypesGridView.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("PRODUCTID_KEY", pid);
                    intent.putExtra("PRODUCTNAME_KEY", pname);
                    intent.putExtra("PRODUCTTYPEID_KEY", ptid);
                    intent.putExtra("PRODUCTTYPE_KEY", ptname);
                    intent.putExtra("ProductTypeGridList", mySQLDataBases);
                    intent.putExtra("ProductTypeList",mySQLDataBases1);
                    c.startActivity(intent);
                }
            }
        }
        private int parseData()
        {
            try
            {
                JSONArray typesGridArray=new JSONArray(jsonData);
                JSONObject typesGridObject;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;

                for(int i=0;i<typesGridArray.length();i++)
                {
                    typesGridObject=typesGridArray.getJSONObject(i);
                    Log.d("result response: ", "> " + typesGridObject);
                    int ProductSizeImageId = typesGridObject.getInt("ProductSizeImageId");
                    String Name =typesGridObject.getString("Name");
                    String ImageUrl=typesGridObject.getString("ImagePath");
                    String Brands = typesGridObject.getString("Brand");
                    String Color = typesGridObject.getString("Color");
                    int ProductSizeId = typesGridObject.optInt("ProductSizeId");
                    int ProductSubTypeId = typesGridObject.optInt("ProductSubTypeId");
                    int ProductTypeId = typesGridObject.getInt("ProductTypeId");
                    int ProductId = typesGridObject.getInt("ProductId");
                    mySQLDataBase=new MySQLDataBase();
                    mySQLDataBase.setProductSizeId(ProductSizeImageId);
                    mySQLDataBase.setName(Name);
                    mySQLDataBase.setImagePath(ImageUrl);
                    mySQLDataBase.setBrand(Brands);
                    mySQLDataBase.setColor(Color);
                    mySQLDataBase.setProductSizeId(ProductSizeId);
                    mySQLDataBase.setProductSubTypeId(ProductSubTypeId);
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
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
       // alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setTitle("Are you sure you want to logout?");
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
                        Intent intent = new Intent(ProductTypes.this, MainActivity.class);
                       // intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent.putExtra("finish",true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
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


