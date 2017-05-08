package com.example.admin.abc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductTypeImages extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = Config.productTypeImgUrlAddress;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_images);

        final GridView gv = (GridView) findViewById(R.id.gv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        final int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        final String pname = intent.getExtras().getString("PRODUCTNAME_KEY");

        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(pid))
                .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(ptid))
                .build();
        URL urlAddress = null;
        try {
            urlAddress = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new ProductTypeImagesDownloader(ProductTypeImages.this,urlAddress,gv,pid,ptid,pname).execute();
        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ProductTypeImages.this,ProductTypes.class);
                    /*in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY",pname);
                     startActivity(in);*/
                    finish();
                }
            });
            actionbar.inflateMenu(R.menu.gridproducts);


            actionbar.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item click event

                            int id = item.getItemId();

                            if (id == R.id.gridadd) {
                                Intent in = new Intent(ProductTypeImages.this, AddGridSubTypes.class);
                                startActivity(in);
                            }
                            if (id == R.id.griddelete) {
                                Intent in = new Intent(ProductTypeImages.this, DeleteProducts.class);
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
                Intent in=new Intent(ProductTypeImages.this,ProductTypeSubTypes.class);
                in.putExtra("PRODUCTID_KEY", pid);
                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                startActivity(in);
            }
        });*/
    }
}
