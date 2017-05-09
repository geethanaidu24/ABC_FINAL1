package com.example.admin.abc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Geetha on 4/20/2017 for displaying sub menu for product types of main products.
 */

public class ProductSubTypes extends AppCompatActivity {
    ImageView back;

    //Context c;
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
                    });

        }
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
            Intent in = new Intent(ProductSubTypes.this, AddProductsSubType.class);

            startActivity(in);
            return true;
        } else if (id == R.id.productdelete) {
            Intent inn = new Intent(ProductSubTypes.this, DeleteProductSubTypes.class);
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





