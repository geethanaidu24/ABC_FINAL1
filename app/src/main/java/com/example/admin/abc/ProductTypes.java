package com.example.admin.abc;

/**
 * Created by Geetha on 4/10/2017 for opening Product types activity based on user clicked product .
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ProductTypes extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url =Config.productTypesUrlAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types);
        LinearLayout ll = (LinearLayout) findViewById(R.id.products_type);
        final ListView lv = (ListView) findViewById(R.id.productTypesLv);
        TextView typeNameTxt= (TextView) findViewById(R.id.SelProductName);
        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
        final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
       final String name = intent.getExtras().getString("PRODUCTNAME_KEY");
        typeNameTxt.setText(name);
        Log.d("result PID: ", "> " + pid);
        String urlAddress = url + pid;

        new ProductTypesDownloader(ProductTypes.this,urlAddress,lv,ll,pid,name).execute();

        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ProductTypes.this,Products.class);
                    finish();
                   // startActivity(in);
                }
            });

            actionbar.inflateMenu(R.menu.productstypes);


            actionbar.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item click event

                            int id = item.getItemId();

                            if (id == R.id.producttypesadd) {
                                Intent in = new Intent(ProductTypes.this, AddProductsTypes.class);
                                in.putExtra("PRODUCTID_KEY", pid);
                                in.putExtra("PRODUCTNAME_KEY",name);
                                startActivity(in);
                            }
                            if (id == R.id.producttypesdelete) {
                                Intent in = new Intent(ProductTypes.this, DeleteProductTypes.class);
                                in.putExtra("PRODUCTID_KEY", pid);
                                in.putExtra("PRODUCTNAME_KEY",name);
                                startActivity(in);
                            }
                            return true;
                        }
                    });

        }
    }

}

