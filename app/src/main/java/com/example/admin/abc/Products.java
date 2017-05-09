package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import static com.example.admin.abc.R.layout.item;

public class Products extends AppCompatActivity {
    private static final int ADD_MENU_ITEM = 0;
    Menu menu;
    ImageView back;
    private boolean loggedIn = true;

    final static String urlAddress = Config.productsUrlAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        //getSupportActionBar().hide();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        final ListView lv = (ListView) findViewById(R.id.productLv);

        new ProductsDownloader(Products.this, urlAddress, lv).execute();
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
        }
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
        toolbar.setOverflowIcon(drawable);
        // toolbar.hideOverflowMenu();
    }

    /* public boolean onPrepareOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.mainproducts, menu);


         if(loggedIn==true){
             MenuItem item = menu.findItem(R.id.productsadd);
             item.setVisible(true);
             MenuItem items = menu.findItem(R.id.productdelete);
             items.setVisible(true);

         }
         else {
           return false;

         }

         return true;
     }*/
    public boolean onCreateOptionsMenu(Menu menu) {

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
                /*if (id == R.id.logout) {
                    Intent innn = new Intent(Products.this, AddProducts.class);
                    startActivity(innn);
                    return true;   */

    }

        return super.onOptionsItemSelected(item);
    }
}






