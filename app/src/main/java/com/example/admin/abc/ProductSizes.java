package com.example.admin.abc;

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

/**
 * Created by Geetha on 4/20/2017 for displaying main product sizes.
 */

public class ProductSizes extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = Config.productSizesUrlAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_sizes);

        LinearLayout ll = (LinearLayout) findViewById(R.id.products_size);

        final ListView lv = (ListView) findViewById(R.id.productSizesLv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

       final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        Log.d("result PID: ", "> " + pid);

        String urlAddress = url + pid;

        new ProductSizesDownloader(ProductSizes.this,urlAddress,lv,ll,pid).execute();
        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ProductSizes.this,Products.class);
                    startActivity(in);
                }
            });
            actionbar.inflateMenu(R.menu.sizes);


            actionbar.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item click event

                            int id = item.getItemId();

                            if (id == R.id.gridadd) {
                                Intent in = new Intent(ProductSizes.this, AddTypesSizes.class);
                                startActivity(in);
                            }
                            if (id == R.id.griddelete) {
                                Intent in = new Intent(ProductSizes.this, DeleteProducts.class);
                                startActivity(in);
                            }
                            return true;
                        }
                    });
        }
        /*back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductSizes.this,Products.class);
                startActivity(in);
            }
        });*/
    }
}
