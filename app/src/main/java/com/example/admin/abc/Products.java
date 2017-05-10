package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.util.ArrayList;
import static com.example.admin.abc.R.layout.item;

public class Products extends AppCompatActivity implements Serializable {
    private static final int ADD_MENU_ITEM = 0;
    Menu menu;
    ImageView back;
    private boolean loggedIn = false;

    final static String urlAddress = Config.productsUrlAddress;
    final static String productsUrl = Config.productsUrlAddress;
    final static String productTypesUrl =Config.productTypesUrlAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        //getSupportActionBar().hide();
        // Intent in=getIntent();
        // final String loggedin = String.valueOf(in.getExtras().getInt("loggedIn"));

        final ListView productsListView = (ListView) findViewById(R.id.productLv);


        // new ProductsDownloader(Products.this, urlAddress, lv).execute();

        new ProductsDownloader(Products.this, productsUrl, productsListView).execute();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Products.this, Main2Activity.class);
                    // startActivity(in);

                    finish();
                }
            });

            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
            toolbar.setOverflowIcon(drawable);
            // toolbar.hideOverflowMenu();
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
            Intent in = new Intent(Products.this, AddProducts.class);
            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(Products.this, DeleteProducts.class);
            startActivity(inn);


            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ProductsDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        String urlAddress;
        ListView lv;

        private ProductsDownloader(Context c, String urlAddress, ListView lv) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.lv = lv;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String data = downloadData();
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
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
                InputStream readjsonData = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(readjsonData));
                String line;
                StringBuffer jsonData = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    jsonData.append(line + "n");
                }
                br.close();
                readjsonData.close();
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

        ArrayList<ProductsDB> productsDBs=new ArrayList<>();
        private ProductsDataParser(Context c, ListView lv, String jsonData) {
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
            {
                Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
            }else
            {

                final ProductsListAdapter adapter=new ProductsListAdapter(c,productsDBs);
                Log.d("productDBS result: ", "> " + productsDBs);
                lv.setAdapter(adapter);
            }
        }

        private int parseData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                productsDBs.clear();
                ProductsDB productsDB;
                for(int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);
                    int ProductId=jo.getInt("ProductId");
                    String ProductName =jo.getString("ProductName");
                    String ImageUrl=jo.getString("ImageUrl");
                    productsDB=new ProductsDB();
                    productsDB.setId(ProductId);
                    productsDB.setName(ProductName);
                    productsDB.setImageUrl(ImageUrl);
                    productsDBs.add(productsDB);
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
        ArrayList<ProductsDB> productsDBs;
        LayoutInflater inflater;

        private ProductsListAdapter(Context c, ArrayList<ProductsDB> productsDBs) {
            this.c = c;
            this.productsDBs = productsDBs;
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return productsDBs.size();
        }

        @Override
        public Object getItem(int position) {
            return productsDBs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.productimage_list_view, parent, false);
            }
            TextView nametxt = (TextView) convertView.findViewById(R.id.textViewURL);
            ImageView img = (ImageView) convertView.findViewById(R.id.imageDownloaded);
            //BIND DATA
            ProductsDB productsDB = (ProductsDB) this.getItem(position);
            final String productname = productsDB.getName();
            final String imageUrl = productsDB.getImageUrl();
            final int productid = productsDB.getId();
            final String finalUrl = Config.mainUrlAddress + imageUrl;
            nametxt.setText(productsDB.getName());

            //IMG
            PicassoClient.downloadImage(c, finalUrl, img);

            // testing new activity condition
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivityCondition(productid, productname);
                }
            });


            return convertView;
        }

        public void openActivityCondition(int recivedPid, String recivedPname) {


          final String ConditionUrlAddress = productTypesUrl + recivedPid;
          new ProductTypesDownloader(Products.this, ConditionUrlAddress, recivedPid, recivedPname).execute();

        }
    }

    private class ProductTypesDownloader extends AsyncTask<Void, Void, String> {
        Context c;
        String productTypesurlAddress;
        int selectedPid;
        String selectedPname;
        private ProductTypesDownloader(Context c, String urlAddress, int recivedPid,String recivedPname) {
            this.c = c;
            this.productTypesurlAddress = urlAddress;
            this.selectedPid = recivedPid;
            this.selectedPname=recivedPname;
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
            if (s == null) {
                Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
            } else {
                //CALL DATA PARSER TO PARSE
              ProductTypesDataParser parser = new ProductTypesDataParser(c, s, selectedPid, selectedPname);
                parser.execute();
            }
        }

        private String downloadTypeData() {
            HttpURLConnection con = Connector.connect(productTypesurlAddress);
            if (con == null) {
                return null;
            }
            try {
                InputStream producttypereader = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(producttypereader));
                String line;
                StringBuffer jsonData = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    jsonData.append(line + "n");
                }
                br.close();
                producttypereader.close();
                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private class ProductTypesDataParser extends AsyncTask<Void,Void,Integer> implements Serializable{
        Context c;
        String jsonData;
        int finalPid;
        String finalPname;
        ArrayList<ProductTypesDB> productTypesDBs=new ArrayList<>();

        private ProductTypesDataParser(Context c,String jsonData, int selectdPid, String selectdPname) {
            this.c = c;
            this.jsonData = jsonData;
            this.finalPid = selectdPid;
            this.finalPname = selectdPname;

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(Void... params) {
            return this.parseProductTypesData();
        }
        @Override

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if(result==0)
            {
                Intent intent = new Intent(c,ProductSizes.class);
                intent.putExtra("PRODUCTID_KEY", finalPid);
                c.startActivity(intent);
            }else
            {
              //  Bundle b = new Bundle();
               // b.putParcelableArrayList("productTypes", productTypesDBLists);
                Intent intent = new Intent(c,ProductTypes.class);
                intent.putExtra("PRODUCTID_KEY", finalPid);
                intent.putExtra("PRODUCTNAME_KEY",finalPname);
               // intent.putExtras(b);
                intent.putExtra("ProductTypeList",productTypesDBs);
                c.startActivity(intent);
            }
        }
        private int parseProductTypesData()
        {
            try
            {
                JSONArray ja=new JSONArray(jsonData);
                JSONObject jo=null;
                productTypesDBs.clear();
                ProductTypesDB productTypesDB;
                for(int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);
                    Log.d("result response: ", "> " + jo);
                    int ProductTypeId=jo.getInt("ProductTypeId");
                    String ProductType =jo.getString("ProductType");
                    String ImageUrl=jo.getString("ImageUrl");
                    int ProductId = jo.getInt("ProductId");
                    productTypesDB=new ProductTypesDB();
                    productTypesDB.setProductTypeId(ProductTypeId);
                    productTypesDB.setProductType(ProductType);
                    productTypesDB.setImageUrl(ImageUrl);
                    productTypesDB.setProductId(ProductId);
                    productTypesDBs.add(productTypesDB);

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
                        Intent intent = new Intent(Products.this, MainActivity.class);
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






