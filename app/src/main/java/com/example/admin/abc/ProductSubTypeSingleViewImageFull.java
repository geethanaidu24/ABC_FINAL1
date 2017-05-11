package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Admin on 4/24/2017.
 */

public  class ProductSubTypeSingleViewImageFull  extends AppCompatActivity {
    ImageView im;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_size_single_view_full_image);



        im = (ImageView) findViewById(R.id.fullimage);

        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        final int pstid = i.getExtras().getInt("PRODUCTSUBTYPEID_KEY");
        final String name = i.getExtras().getString("NAME_KEY");
        final String image = i.getExtras().getString("IMAGE_KEY");
        final String brand = i.getExtras().getString("BRAND_KEY");
        final String color = i.getExtras().getString("COLOR_KEY");
        PicassoClient.downloadImage(c,image,im);

        // Picasso.with(c).load("http://192.168.0.3/abc/getProductSizeImages.php?").into(im);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductSubTypeSingleViewImageFull.this, ProductSubTypeSingleViewActivity.class);
/*
                    in.putExtra("IMAGE_KEY",image);
                    in.putExtra("PRODUCTID_KEY",pid);
                    in.putExtra("PRODUCTNAME_KEY",pname);
                    in.putExtra("PRODUCTTYPEID_KEY",ptid);
                    in.putExtra("PRODUCTTYPENAME_KEY",ptname);
                    in.putExtra("PRODUCTSUBTYPEID_KEY",pstid);
                    in.putExtra("NAME_KEY",name);
                    in.putExtra("BRAND_KEY",brand);
                    in.putExtra("COLOR_KEY",color);
                    startActivity(in);*/
finish();
                }
            });

        }
    }
}
