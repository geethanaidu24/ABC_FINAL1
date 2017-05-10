package com.example.admin.abc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Geetha on 4/18/2017 for opening size typed images.
 */

public class ProductTypeSizeImages extends AppCompatActivity {
    ImageView back;

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


        new ProductTypeSizeImagesDownloader(ProductTypeSizeImages.this,urlAddress,gv,pid,pname,ptid,ptsid).execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ProductTypeSizeImages.this,ProductTypes.class);
                    /*in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY",pname);
                    in.putExtra("PRODUCTTYPEID_KEY",ptid);

                    startActivity(in);*/
                    finish();
                }
            });
            /*actionbar.inflateMenu(R.menu.gridproducts);


            actionbar.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item click event

                            int id = item.getItemId();

                            if (id == R.id.gridadd) {
                                Intent in = new Intent(ProductTypeSizeImages.this, AddGridSubTypes.class);
                                startActivity(in);
                            }
                            if (id == R.id.griddelete) {
                                Intent in = new Intent(ProductTypeSizeImages.this, DeleteProducts.class);
                                startActivity(in);
                            }
                            return true;
                        }
                    });
        }
       *//* back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductTypeSizeImages.this,ProductTypeSizes.class);
                startActivity(in);
            }
        });*//*
    }
}*/
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
            toolbar.setOverflowIcon(drawable);

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.mainproducts, menu);

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
            Intent in = new Intent(ProductTypeSizeImages.this, AddGridSubTypes.class);

            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(ProductTypeSizeImages.this, DeleteProducts.class);
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