package com.example.admin.abc;

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
import android.widget.GridView;
import android.widget.ImageView;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductTypesGridView extends AppCompatActivity implements Serializable{
    ImageView back;
    Context c;
    private boolean loggedIn = false;
    private int selectedProducttypeid;
    private String selectedProducttype;
    private static int selectedPid;
    private static String selectedPname;

    final static String url = Config.productTypeImgUrlAddress;


    @Override
    public void onCreate(Bundle savedInstanceState) {
      //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_images);

        final GridView gv = (GridView) findViewById(R.id.gv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
        selectedPname = intent.getExtras().getString("PRODUCTNAME_KEY");
        selectedPid = intent.getExtras().getInt("PRODUCTID_KEY");
        selectedProducttype = intent.getExtras().getString("PRODUCTTYPE_KEY");
        selectedProducttypeid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");

        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(selectedPid))
                .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(selectedProducttypeid))
                
                .build();
        URL urlAddress = null;
        try {
            urlAddress = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new ProductTypeImagesDownloader(ProductTypesGridView.this,urlAddress,gv,selectedPid,selectedProducttypeid).execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductTypesGridView.this, ProductTypes.class);

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
            Intent in = new Intent(ProductTypesGridView.this, AddGridProductTypes.class);
            in.putExtra("PRODUCTID_KEY",selectedPid);
            in.putExtra("PRODUCTNAME_KEY",selectedPname);
            in.putExtra("PRODUCTTYPEID_KEY",selectedProducttypeid);
            in.putExtra("PRODUCTTYPE_KEY",selectedProducttype);
            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(ProductTypesGridView.this, DeleteGridProductTypes.class);
            inn.putExtra("PRODUCTTYPEID_KEY",selectedProducttypeid);
            startActivity(inn);

            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class ProductTypeImagesDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        URL urlAddress;
        GridView gv;
        int pid;
        int ptid;
        private ProductTypeImagesDownloader(Context c, URL urlAddress, GridView gv, int pid, int ptid) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.gv = gv;
            this.pid = pid;
            this.ptid =ptid;
            Log.d("newActivity url: ", "> " + urlAddress);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String data = downloadTypeData();
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
                ProductTypeImagesDataParser parser=new ProductTypeImagesDataParser(c, gv, s,pid,ptid);
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
    private class ProductTypeImagesDataParser extends AsyncTask<Void,Void,Integer> {
        Context c;
        GridView gv;
        String jsonData;
        int pid,ptid;
        ArrayList<ProductTypeSubTypeImageItem> productTypeSubTypeImageItems=new ArrayList<>();

        private ProductTypeImagesDataParser(Context c, GridView gv, String jsonData, int pid, int ptid) {
            this.c = c;
            this.gv = gv;
            this.jsonData = jsonData;
            this.pid=pid;
            this.ptid=ptid;
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
                Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
            }else
            {
                final ProductTypeImagesGirdAdapter adapter=new ProductTypeImagesGirdAdapter(c,productTypeSubTypeImageItems,pid,ptid);
                gv.setAdapter(adapter);

            }
        }
        private int parseData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                productTypeSubTypeImageItems.clear();
                ProductTypeSubTypeImageItem productTypeSubTypeImageItem;

                for(int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);
                    int ProductSizeImageId = jo.getInt("ProductSizeImageId");
                    String Name =jo.getString("Name");
                    String ImageUrl=jo.getString("ImagePath");
                    String Brands = jo.getString("Brand");
                    String Color = jo.getString("Color");
                    int ProductSizeId = jo.optInt("ProductSizeId");
                    int ProductSubTypeId = jo.optInt("ProductSubTypeId");
                    int ProductTypeId = jo.getInt("ProductTypeId");
                    int ProductId = jo.getInt("ProductId");
                    productTypeSubTypeImageItem=new ProductTypeSubTypeImageItem();
                    productTypeSubTypeImageItem.setProductSizeId(ProductSizeImageId);
                    productTypeSubTypeImageItem.setName(Name);
                    productTypeSubTypeImageItem.setImagePath(ImageUrl);
                    productTypeSubTypeImageItem.setBrand(Brands);
                    productTypeSubTypeImageItem.setColor(Color);
                    productTypeSubTypeImageItem.setProductSizeId(ProductSizeId);
                    productTypeSubTypeImageItem.setProductSubTypeId(ProductSubTypeId);
                    productTypeSubTypeImageItem.setProductTypeId(ProductTypeId);
                    productTypeSubTypeImageItem.setProductId(ProductId);
                    productTypeSubTypeImageItems.add(productTypeSubTypeImageItem);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    private class ProductTypeImagesGirdAdapter extends BaseAdapter {
        Context c;

        ArrayList<ProductTypeSubTypeImageItem> productTypeSubTypeImageItems;
        LayoutInflater inflater;
        int pid,ptid;
        private ProductTypeImagesGirdAdapter(Context c, ArrayList<ProductTypeSubTypeImageItem> productTypeImageItems, int pid,int ptid) {
            this.c = c;
            this.productTypeSubTypeImageItems = productTypeImageItems;
            this.pid=pid;
            this.ptid=ptid;
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return productTypeSubTypeImageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return productTypeSubTypeImageItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.gridview_layout, parent, false);
            }
            TextView typeNameTxt = (TextView) convertView.findViewById(R.id.txtTypeSizePro);
            ImageView img = (ImageView) convertView.findViewById(R.id.imgTypeSizePro);
            //BIND DATA
            ProductTypeSubTypeImageItem productTypeSubTypeImageItem = (ProductTypeSubTypeImageItem) this.getItem(position);

            final int imageid = productTypeSubTypeImageItem.getProductSizeImageId();
            typeNameTxt.setText(productTypeSubTypeImageItem.getName());

            final String url = productTypeSubTypeImageItem.getImagePath();
            final String finalUrl=Config.mainUrlAddress + url;
            try {
                java.net.URLEncoder.encode(url,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //IMG
            PicassoClient.downloadImage(c, finalUrl, img);
            //BIND DATA
            final String name = productTypeSubTypeImageItem.getName();

            final String brand = productTypeSubTypeImageItem.getBrand();
            final String color = productTypeSubTypeImageItem.getColor();
            final int protypeid = productTypeSubTypeImageItem.getProductTypeId();
            final int proid = productTypeSubTypeImageItem.getProductId();
            // open new activity
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //open detail activity
                    // startDeatilActivity();
                    openDetailActivity(pid,ptid,name,finalUrl,brand,color);
                }
            });
            return convertView;
        }
        private void openDetailActivity(int pid, int ptid, String...details)
        {
            Intent i = new Intent(c,ProductTypeSingleViewActivity.class);
            i.putExtra("PRODUCTID_KEY", pid);
            i.putExtra("PRODUCTTYPEID_KEY", ptid);
            i.putExtra("NAME_KEY", details[0]);
            i.putExtra("IMAGE_KEY",details[1]);
            i.putExtra("BRAND_KEY", details[2]);
            i.putExtra("COLOR_KEY", details[3]);
            c.startActivity(i);
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
                        Intent intent = new Intent(ProductTypesGridView.this, MainActivity.class);
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