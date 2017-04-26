package com.example.admin.abc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductSizeImages extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url =Config.productSizeImgUrlAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_sizes_images);

        final GridView gv = (GridView) findViewById(R.id.gv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
       final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
      final  int psid = intent.getExtras().getInt("PRODUCTSIZEID_KEY");
        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(pid))
                .appendQueryParameter(Config.PRODUCTSIZEID_PARAM, Integer.toString(psid))
                .build();
        URL urlAddress = null;
        try {
            urlAddress = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        new ProductSizeImagesDownloader(ProductSizeImages.this,urlAddress,gv,pid,psid).execute();
        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ProductSizeImages.this,ProductSizes.class);
                    in.putExtra("PRODUCTID_KEY",pid);
                    startActivity(in);
                }
            });

            // Inflate a menu to be displayed in the toolbar
          //  actionbar.inflateMenu(R.menu.actions);
        }
        /*back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductSizeImages.this,ProductSizes.class);
                in.putExtra("PRODUCTID_KEY",pid);
                startActivity(in);
            }
        });*/
    }
}
