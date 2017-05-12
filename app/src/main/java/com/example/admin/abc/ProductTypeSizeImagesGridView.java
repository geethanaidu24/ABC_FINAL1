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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Geetha on 4/18/2017 for opening size typed images.
 */

public class ProductTypeSizeImagesGridView extends AppCompatActivity {
    ImageView back;
    private boolean loggedIn = false;
    //Context c;
    final static String url =Config.productTypeSizeImgUrlAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_sizes_images);

        final GridView gv = (GridView) findViewById(R.id.gv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
       final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        final String pname =intent.getExtras().getString("PRODUCTNAME_KEY");
      final  int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
     final  int ptsid = intent.getExtras().getInt("PRODUCTTYPESIZEID_KEY");

        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(pid))
                .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(ptid))
                .appendQueryParameter(Config.PRODUCTSIZEID_PARAM, Integer.toString(ptsid))
                .build();
        URL urlAddress = null;
        try {
            urlAddress = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        new ProductTypeSizeImagesDownloader(ProductTypeSizeImagesGridView.this,urlAddress,gv,pid,pname,ptid,ptsid).execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ProductTypeSizeImagesGridView.this,ProductTypes.class);
                    /*in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY",pname);
                    in.putExtra("PRODUCTTYPEID_KEY",ptid);

                    startActivity(in);*/
                    finish();
                }
            });
              Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
            toolbar.setOverflowIcon(drawable);

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, true);
        // Inflate the menu; this adds items to the action bar if it is present.
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
            Intent in = new Intent(ProductTypeSizeImagesGridView.this, AddGridProductTypeSizes.class);

            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(ProductTypeSizeImagesGridView.this, DeleteGridProductTypeSizes.class);
            startActivity(inn);

            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    private class ProductTypeSizeImagesDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        URL urlAddress;
        GridView gv;
        int pid,ptid,ptsid;
        String pname;

        public ProductTypeSizeImagesDownloader(Context c, URL urlAddress, GridView gv,int pid, String pname, int ptid, int ptsid) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.gv = gv;
            this.pid = pid;
            this.ptid = ptid;
            this.pname = pname;
            this.ptsid = ptsid;

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
                ProductTypeSizeImagesDataParser parser=new ProductTypeSizeImagesDataParser(c, gv, s,pid,pname,ptid,ptsid);
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
    private class ProductTypeSizeImagesDataParser  extends AsyncTask<Void,Void,Integer> {
        Context c;
        GridView gv;
        String jsonData;
        int pid,ptid,ptsid;
        String pname;
        ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems=new ArrayList<>();

        public ProductTypeSizeImagesDataParser(Context c, GridView gv, String jsonData,int pid,String pname, int ptid,int ptsid) {
            this.c = c;
            this.gv = gv;
            this.jsonData = jsonData;
            this.pid=pid;
            this.pname=pname;
            this.ptid=ptid;
            this.ptsid=ptsid;

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

                final ProductTypeSizeImagesGirdAdapter adapter=new ProductTypeSizeImagesGirdAdapter(c,productTypeSizeImageItems,pid,pname,ptid,ptsid);
                gv.setAdapter(adapter);

            }
        }
        private int parseData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                productTypeSizeImageItems.clear();
                ProductTypeSizeImageItem productTypeSizeImageItem;
                for(int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);

                    int ImageId=jo.getInt("ProductSizeImageId");
                    String Name =jo.getString("Name");
                    String ImageUrl=jo.getString("ImagePath");
                    String Brands = jo.getString("Brand");
                    String Color = jo.getString("Color");
                    int SizeId = jo.getInt("ProductSizeId");
                    int ProductSTypeId = jo.optInt("ProductSubTypeId", 0);
                    int ProductTypeId = jo.getInt("ProductTypeId");
                    int ProductId = jo.getInt("ProductId");
                    int Width = jo.getInt("Width");
                    int Height = jo.getInt("Height");
                    int Length = jo.getInt("Length");

                    productTypeSizeImageItem=new ProductTypeSizeImageItem();
                    productTypeSizeImageItem.setProductSizeImageId(ImageId);
                    productTypeSizeImageItem.setName(Name);
                    productTypeSizeImageItem.setImagePath(ImageUrl);
                    productTypeSizeImageItem.setBrand(Brands);
                    productTypeSizeImageItem.setColor(Color);
                    productTypeSizeImageItem.setProductSizeId(SizeId);
                    productTypeSizeImageItem.setProductSubTypeId(ProductSTypeId);
                    productTypeSizeImageItem.setProductTypeId(ProductTypeId);
                    productTypeSizeImageItem.setProductId(ProductId);
                    productTypeSizeImageItem.setWidth(Width);
                    productTypeSizeImageItem.setHeight(Height);
                    productTypeSizeImageItem.setLength(Length);
                    productTypeSizeImageItems.add(productTypeSizeImageItem);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    private static class ProductTypeSizeImagesGirdAdapter extends BaseAdapter {
        Context c;

        ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems;
        LayoutInflater inflater;
        static String finalSize ;
        int pid,ptid,ptsid;
        String pname;
        private ProductTypeSizeImagesGirdAdapter(Context c, ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems, int pid, String pname, int ptid, int ptsid) {
            this.c = c;
            this.productTypeSizeImageItems = productTypeSizeImageItems;
            this.pid= pid;
            this.pname=pname;
            this.ptid=ptid;
            this.ptsid=ptsid;
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return productTypeSizeImageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return productTypeSizeImageItems.get(position);
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
            ProductTypeSizeImageItem productTypeSizeImageItem = (ProductTypeSizeImageItem) this.getItem(position);

            typeNameTxt.setText(productTypeSizeImageItem.getName());

            //IMG
            final String url = productTypeSizeImageItem.getImagePath();
            final String finalUrl=Config.mainUrlAddress + url;
            PicassoClient.downloadImage(c,finalUrl, img);
            //BIND DATA
            final String name = productTypeSizeImageItem.getName();

            final String brand = productTypeSizeImageItem.getBrand();
            final String color = productTypeSizeImageItem.getColor();
            final int sizeid = productTypeSizeImageItem.getProductSizeId();
            final int width = Integer.parseInt(String.valueOf(productTypeSizeImageItem.getWidth()).toString());
            final int height = Integer.parseInt(String.valueOf(productTypeSizeImageItem.getHeight()).toString());
            final int length = Integer.parseInt(String.valueOf(productTypeSizeImageItem.getLength()).toString());




            if(length !=0 && width !=0 && height !=0){
                finalSize =  width + "X" + height + "X" + length;

            }else if(length ==0 && width !=0 && height !=0){
                finalSize =  width + "X" + height;

            }else if(length !=0 && width ==0 && height !=0){
                finalSize =  length + "X" + height;

            }else if(length !=0 && width !=0 && height ==0 ){
                finalSize =  length + "X" + width ;

            }else if(length ==0 && width !=0 && height ==0 ){
                finalSize = width + "" ;

            }else if(length !=0 && width ==0 && height ==0 ){
                finalSize = length + "" ;

            }else if(length ==0 && width ==0 && height !=0 ){
                finalSize = height + "" ;

            }

            // open new activity
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //open detail activity
                    openDetailActivity(pid,pname,ptid,ptsid,name,finalUrl,brand,color,finalSize);
                }
            });
            return convertView;
        }
        private void openDetailActivity(int pid,String pname,int ptid,int ptsid,String...details)
        {
            Intent i = new Intent(c,ProductTypeSizeSingleViewFullDetails.class);
            i.putExtra("PRODUCTID_KEY",pid);
            i.putExtra("PRODUCTNAME_KEY",pname);
            i.putExtra("PRODUCTTYPEID_KEY",ptid);
            i.putExtra("PRODUCTTYPESIZEID_KEY",ptsid);
            i.putExtra("NAME_KEY", details[0]);
            i.putExtra("IMAGE_KEY",details[1]);
            i.putExtra("BRAND_KEY", details[2]);
            i.putExtra("COLOR_KEY", details[3]);
            i.putExtra("SIZE_KEY", details[4]);
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
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.KEY_USER, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(ProductTypeSizeImagesGridView.this, MainActivity.class);
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

