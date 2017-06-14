package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by Geetha on 4/20/2017 for displaying main product sizes.
 */

public class ProductSizes extends AppCompatActivity {
    ImageView back;
    Context c;
    int click=0;
    private boolean loggedIn = false;
    private static int selectdProductId;
    private static String selectdProductName, finalProductSelctedSize;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_sizes);

        LinearLayout ll = (LinearLayout) findViewById(R.id.products_size);

        final ListView lv = (ListView) findViewById(R.id.productSizesLv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        selectdProductId = intent.getExtras().getInt("PRODUCTID_KEY");
        selectdProductName = intent.getExtras().getString("PRODUCTNAME_KEY");
        Log.d("result PID: ", "> " + selectdProductId);
        ArrayList<MySQLDataBase> mySQLDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductSizeList");

        final ProductSizesListAdapter adapter = new ProductSizesListAdapter(this, mySQLDataBases, selectdProductId);
        lv.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductSizes.this, Products.class);
                    finish();
                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
            toolbar.setOverflowIcon(drawable);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
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
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent in = new Intent(ProductSizes.this, AddProductSizes.class);
                in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                in.putExtra("PRODUCTID_KEY", selectdProductId);
                in.putExtra("PRODUCTNAME_KEY", selectdProductName);
                startActivity(in);
                return true;
            }
        } else if (id == R.id.productdelete) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent inn = new Intent(ProductSizes.this, DeleteProductSizes.class);
                inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                inn.putExtra("PRODUCTID_KEY", selectdProductId);
                startActivity(inn);

                return true;
            }
        } else if (id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        //finishAffinity();
        Intent in = new Intent(ProductSizes.this, Products.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();



    }


    private class ProductSizesListAdapter extends BaseAdapter {

        Context c;
        ArrayList<MySQLDataBase> mySQLDataBases;
        LayoutInflater inflater;
        int pid;
       // String finalProductSize;

        private ProductSizesListAdapter(Context c, ArrayList<MySQLDataBase> mySQLDataBases, int pid) {
            this.c = c;
            this.mySQLDataBases = mySQLDataBases;
            this.pid = pid;
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
                convertView = inflater.inflate(R.layout.productsize_list_view, parent, false);
            }
            TextView typeNameTxt = (TextView) convertView.findViewById(R.id.productSize);

            //BIND DATA
            MySQLDataBase mySQLDataBase = (MySQLDataBase) this.getItem(position);

            final int sizeid = mySQLDataBase.getProductSizeId();
            final int length = Integer.parseInt(String.valueOf(mySQLDataBase.getLength()).toString());
            final int width = Integer.parseInt(String.valueOf(mySQLDataBase.getWidth()).toString());
            final int height = Integer.parseInt(String.valueOf(mySQLDataBase.getHeight()).toString());
            //final String measure =productTypeSizeDBData.getMeasurement().toString();

            if (length != 0 && width != 0 && height != 0) {
                finalProductSelctedSize = width + "X" + height + "X" + length;
                typeNameTxt.setText(String.valueOf(finalProductSelctedSize));
            } else if (length == 0 && width != 0 && height != 0) {
                finalProductSelctedSize = width + "X" + height;
                typeNameTxt.setText(String.valueOf(finalProductSelctedSize));
            } else if (length != 0 && width == 0 && height != 0) {
                finalProductSelctedSize = length + "X" + height;
                typeNameTxt.setText(String.valueOf(finalProductSelctedSize));
            } else if (length != 0 && width != 0 && height == 0) {
                finalProductSelctedSize = length + "X" + width;
                typeNameTxt.setText(String.valueOf(finalProductSelctedSize));
            } else if (length == 0 && width != 0 && height == 0) {
                finalProductSelctedSize = width + "";
                typeNameTxt.setText(finalProductSelctedSize);
            } else if (length != 0 && width == 0 && height == 0) {
                finalProductSelctedSize = length + "";
                typeNameTxt.setText(finalProductSelctedSize);
            } else if (length == 0 && width == 0 && height != 0) {
                finalProductSelctedSize = height + "";
                typeNameTxt.setText(finalProductSelctedSize);
            }
            // open new activity
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProductSizeImagesActivity(sizeid,length,width,height,finalProductSelctedSize);
                }
            });

            return convertView;
        }

        public void openProductSizeImagesActivity(int sizeid,int length,int width,int height,String finalSize) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent intent = new Intent(c, ProductSizeGridViewImages.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("PRODUCTID_KEY", selectdProductId);
                intent.putExtra("PRODUCTNAME_KEY", selectdProductName);
                intent.putExtra("PRODUCTSIZEID_KEY", sizeid);
                intent.putExtra("PRODUCTSIZEWIDTH_KEY", width);
                intent.putExtra("PRODUCTSIZELENGTH_KEY", length);
                intent.putExtra("PRODUCTSIZEHEIGHT_KEY", height);
                intent.putExtra("FINALPROSELSIZE_KEY", finalSize);
                c.startActivity(intent);
            }
        }
    }
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //alertDialogBuilder.setMessage("Are you sure you want to logout?");
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
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(ProductSizes.this, MainActivity.class);
                        intent.putExtra("finish",true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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


