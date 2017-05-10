package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.graphics.drawable.Drawable;
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
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Geetha on 4/20/2017 for displaying sub menu for product types of main products.
 */

public class ProductSubTypes extends AppCompatActivity {
    ImageView back;
    private boolean loggedIn = false;

    final static String url = Config.productSubTypesUrlAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_subtypes);

        LinearLayout ll = (LinearLayout) findViewById(R.id.products_subtype);
        final ListView lv = (ListView) findViewById(R.id.productTypeSubTypesLv);
        TextView typeNameTxt = (TextView) findViewById(R.id.SelProductTypeName);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
        final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        final String pname = intent.getExtras().getString("PRODUCTNAME_KEY");
        final int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        final String ptname = intent.getExtras().getString("PRODUCTTYPENAME_KEY");
        typeNameTxt.setText(ptname);
        Log.d("result PID: ", "> " + pid);
        Log.d("result PTID: ", "> " + ptid);

        String urlAddress = url + ptid;

        new ProductSubTypesDownloader(ProductSubTypes.this, urlAddress, lv, ll, pid, pname, ptid, ptname).execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductSubTypes.this, ProductTypes.class);
                   /* in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY", pname);
                    in.putExtra("PRODUCTTYPEID_KEY", ptid);
                    in.putExtra("PRODUCTTYPENAME_KEY", ptname);

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
            Intent in = new Intent(ProductSubTypes.this, AddProductsSubType.class);

            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(ProductSubTypes.this, DeleteProductSubTypes.class);
            startActivity(inn);

            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


           /* // Inflate a menu to be displayed in the toolbar
            actionbar.inflateMenu(R.menu.mainproducts);

            actionbar.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item click event

                            int id = item.getItemId();

                            if (id == R.id.productsadd) {
                                Intent in = new Intent(ProductSubTypes.this, AddProductsSubType.class);
                                in.putExtra("PRODUCTID_KEY", pid);
                                in.putExtra("PRODUCTNAME_KEY", pname);
                                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                                in.putExtra("PRODUCTTYPENAME_KEY", ptname);
                                startActivity(in);
                            }
                            if (id == R.id.productdelete) {
                                Intent in = new Intent(ProductSubTypes.this, DeleteProductSubTypes.class);
                                in.putExtra("PRODUCTID_KEY", pid);
                                in.putExtra("PRODUCTNAME_KEY", pname);
                                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                                in.putExtra("PRODUCTTYPENAME_KEY", ptname);
                                startActivity(in);
                            }
                            return true;
                        }
                    }
            );

        }
    }*/


    private class ProductSubTypesDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        String urlAddress;
        ListView lv;
        LinearLayout ll;
        int ptid;
        int pid;
        String pname, ptname;


        private ProductSubTypesDownloader(Context c, String urlAddress, ListView lv, LinearLayout ll, int pid, String pname, int ptid, String ptname) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.lv = lv;
            this.ll = ll;
            this.ptid = ptid;
            this.pid = pid;
            this.pname = pname;
            this.ptname = ptname;
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
            if (s == null) {
                Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
            } else {
                //CALL DATA PARSER TO PARSE
                ProductSubTypesDataParser parser = new ProductSubTypesDataParser(c, lv, ll, s, pid, pname, ptid, ptname);
                parser.execute();
            }
        }

        private String downloadTypeData() {
            HttpURLConnection con = Connector.connect(urlAddress);
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
        ListView lv;
        LinearLayout ll;
        String jsonData;
        int ptid;
        int pid;
        String pname, ptname;
        ArrayList<ProductSubTypesDB> productSubTypesDBs = new ArrayList<>();

        private ProductSubTypesDataParser(Context c, ListView lv, LinearLayout ll, String jsonData, int pid, String pname, int ptid, String ptname) {
            this.c = c;
            this.lv = lv;
            this.ll = ll;
            this.jsonData = jsonData;
            this.ptid = ptid;
            this.pid = pid;
            this.pname = pname;
            this.ptname = ptname;
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
            if (result == 0) {
                openProductTypesSizesActivity(pid, pname, ptid, ll);
            } else {
                ll.setVisibility(View.VISIBLE);
                final ProductSubTypesListAdapter adapter = new ProductSubTypesListAdapter(c, productSubTypesDBs, pid, pname, ptid, ptname);
                lv.setAdapter(adapter);

            }
        }

        public void openProductTypesSizesActivity(int pid, String pname, int ptid, LinearLayout ll) {
            Intent intent = new Intent(c, ProductTypeSizes.class);
            intent.putExtra("PRODUCTID_KEY", pid);
            intent.putExtra("PRODUCTNAME_KEY", pname);
            intent.putExtra("PRODUCTTYPEID_KEY", ptid);
            c.startActivity(intent);
        }

        private int parseData() {
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

    private class ProductSubTypesListAdapter extends BaseAdapter {

        Context c;

        ArrayList<ProductSubTypesDB> productSubTypesDBs;
        int pid, ptid;
        String pname, ptname;
        LayoutInflater inflater;

        public ProductSubTypesListAdapter(Context c, ArrayList<ProductSubTypesDB> productSubTypesDBs, int pid, String pname, int ptid, String ptname) {
            this.c = c;
            this.productSubTypesDBs = productSubTypesDBs;
            this.pid = pid;
            this.pname = pname;
            this.ptid = ptid;
            this.ptname = ptname;
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return productSubTypesDBs.size();
        }

        @Override
        public Object getItem(int position) {
            return productSubTypesDBs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.producttypesubtypeimage_list_view, parent, false);
            }
            TextView typeNameTxt = (TextView) convertView.findViewById(R.id.txtSubTypePro);
            ImageView img = (ImageView) convertView.findViewById(R.id.imageSubTypePro);
            //BIND DATA
            ProductSubTypesDB productSubTypesDB = (ProductSubTypesDB) this.getItem(position);
            final int pstid = productSubTypesDB.getProductSubTypeId();
            final int ptid = productSubTypesDB.getProductTypeId();
            final String pstname = productSubTypesDB.getProductSubTypeName();
            final String url = productSubTypesDB.getImageUrl();
            final String finalUrl = Config.mainUrlAddress + url;
            typeNameTxt.setText(productSubTypesDB.getProductSubTypeName());
            //IMG
            PicassoClient.downloadImage(c, finalUrl, img);

            // open new activity
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProductTypeSizeImagessActivity(pid, pname, ptid, ptname, pstid);
                }
            });

            return convertView;
        }

        public void openProductTypeSizeImagessActivity(int pid, String pname, int ptid, String ptname, int pstid) {
            Intent intent = new Intent(c, ProductSubTypeGridView.class);
            intent.putExtra("PRODUCTID_KEY", pid);
            intent.putExtra("PRODUCTNAME_KEY", pname);
            intent.putExtra("PRODUCTTYPEID_KEY", ptid);
            intent.putExtra("PRODUCTTYPENAME_KEY", ptname);
            intent.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
            c.startActivity(intent);
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
                        Intent intent = new Intent(ProductSubTypes.this, MainActivity.class);
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









