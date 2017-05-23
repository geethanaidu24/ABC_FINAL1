package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ProductSizeSingleViewFullImage extends AppCompatActivity {
    ImageView im;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_size_single_view_full_image);



            im = (ImageView) findViewById(R.id.fullimage);

            Intent i = this.getIntent(); // get Intent which we set from Previous Activity
            final String image = i.getExtras().getString("IMAGE_KEY");
            final int pid = i.getExtras().getInt("PRODUCTID_KEY");
            final int psid = i.getExtras().getInt("PRODUCTSIZEID_KEY");
            final String name = i.getExtras().getString("NAME_KEY");

            final String brand = i.getExtras().getString("BRAND_KEY");
            final String color = i.getExtras().getString("COLOR_KEY");
            final String size = i.getExtras().getString("SIZE_KEY");
        Glide.with(this)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
                .centerCrop()
                .crossFade()
                .into(im);

           // Picasso.with(c).load("http://192.168.0.3/abc/getProductSizeImages.php?").into(im);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductSizeSingleViewFullImage.this, ProductSizeImageSingleViewFullDetails.class);
                    finish();
                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dots);
            toolbar.setOverflowIcon(drawable);

        }
    }
}
