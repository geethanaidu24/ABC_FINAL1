package com.atwyn.android.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.LruCache;
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
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

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

//import static com.example.admin.abc.R.id.productsadd;

public class Brands extends AppCompatActivity {
int click=0;
    private boolean loggedIn = false;
    private LruCache<String, Bitmap> mMemoryCache;
    //Integer[] imageIDs = {
    //        R.mipmap.brone, R.mipmap.brtwo, R.mipmap.brthree, R.mipmap.brfour, R.mipmap.brone, R.mipmap.brone, R.mipmap.brone, R.mipmap.brone

    //};

    final static String urlAddress = Config.brandsImgUrlAddress;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        final GridView gridView1 = (GridView) findViewById(R.id.brandgrid_view);

        new BrandsDownloader(Brands.this, urlAddress, gridView1).execute();

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

// Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        /*back5 = (ImageView) findViewById(R.id.back);
        back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Brands.this, Main2Activity.class);
                startActivity(in);
            }
        });*/
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
                        Intent in = new Intent(Brands.this, Main2Activity.class);

                       /* in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);*/
                        finish();
                    }
                    //  startActivity(in);
                }
            });


            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ho);
            toolbar.setOverflowIcon(drawable);

        }
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(Brands.this, Main2Activity.class);

            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);*/
            finish();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        //getMenuInflater().inflate(R.menu.brands, menu);
        if (loggedIn) {
            /*MenuItem item = menu.findItem(R.id.productsadd);
            item.setVisible(true);
            *//*MenuItem items = menu.findItem(R.id.productdelete);
            items.setVisible(true);*//*
            MenuItem itemss = menu.findItem(R.id.logout);
            itemss.setVisible(true);
            MenuItem items2 = menu.findItem(R.id.h1);
            items2.setVisible(true);*/
            getMenuInflater().inflate(R.menu.brands, menu);

        } else  {
           /* MenuItem item1 = menu.findItem(productsadd);
            item1.setVisible(false);

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
                Intent in = new Intent(Brands.this, AddBrands.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                        Intent.FLAG_ACTIVITY_NEW_TASK*/);
                startActivity(in);
                return true;
            }
        }/* else if (id == R.id.productdelete) {
            Intent inn = new Intent(Brands.this, DeleteBrands.class);
            startActivity(inn);

            return true;*/
        else if (id == R.id.logout) {
            logout();
            return true;
        }else if(id==R.id.h1)
        {
            Intent inn = new Intent(Brands.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*/);
            startActivity(inn);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
       // alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setTitle("Are you sure you want to logout?");
        alertDialogBuilder.setIcon(R.mipmap.logout);
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
                        editor.apply();

                        //Starting login activity
                        Intent intent = new Intent(Brands.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);

        // ATTENTION: This was auto-generated to implement the App Indexing API.


        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Brands Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class BrandsDownloader extends AsyncTask<Void, Void, String> {

        Context c;
        String urlAddress;
        GridView gridView1;


        private BrandsDownloader(Context c, String urlAddress, GridView gridView1) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.gridView1 = gridView1;
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
                Toast toast = Toast.makeText(c, "Our Brands are Coming Soon! Thank you for your patience", Toast.LENGTH_SHORT);

                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
                //Toast.makeText(c, "Unsuccessful,Null returned", Toast.LENGTH_SHORT).show();
            } else {

                //CALL DATA PARSER TO PARSE
                BrandsDataParser parser = new BrandsDataParser(c, gridView1, s);
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

    private class BrandsDataParser extends AsyncTask<Void, Void, Integer> {
        Context c;

        GridView gridView1;
        String jsonData;

        ArrayList<MySQLDataBase> mySQLDataBases = new ArrayList<>();

        public BrandsDataParser(Context c, GridView gridView1, String jsonData) {
            this.c = c;
            this.gridView1 = gridView1;
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
            if (result == 0) {
                Toast toast = Toast.makeText(c, "Our Brands are Coming Soon! Thank you for your patience", Toast.LENGTH_SHORT);

                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
               // Toast.makeText(c, "No Collection Available", Toast.LENGTH_SHORT).show();
            } else {

                final BrandsListAdapter adapter = new BrandsListAdapter(c, mySQLDataBases);
                gridView1.setAdapter(adapter);
            }
        }

        private int parseData() {
            try {
                JSONArray brandsArray = new JSONArray(jsonData);
                JSONObject brandsObject = null;
                mySQLDataBases.clear();
                MySQLDataBase mySQLDataBase;
                for (int i = 0; i < brandsArray.length(); i++) {
                    brandsObject = brandsArray.getJSONObject(i);
                    Log.d("result response: ", "> " + brandsObject);
                    int BrandId = brandsObject.getInt("ID");
                    String ImageUrl = brandsObject.getString("ImagePath");
                    mySQLDataBase = new MySQLDataBase();
                    mySQLDataBase.setBrandId(BrandId);
                    mySQLDataBase.setBrandImageUrl(ImageUrl);
                    mySQLDataBases.add(mySQLDataBase);

                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }

    }

    private class BrandsListAdapter extends BaseAdapter {

        Context c;
        ArrayList<MySQLDataBase> mySQLDataBases;
        LayoutInflater inflater;

        private BrandsListAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.brands_list_gridview, parent, false);
            }

            ImageView img = (ImageView) convertView.findViewById(R.id.brandgridimg);
            //BIND DATA
            MySQLDataBase mySQLDataBase = (MySQLDataBase) this.getItem(position);
            final String url = mySQLDataBase.getBrandImageUrl();
            final String finalUrl = Config.mainUrlAddress + url;

            //IMG
            PicassoClient.downloadImage(c, finalUrl, img);

            return convertView;
        }

    }
}

