package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductSizeImageSingleViewFullDetails extends AppCompatActivity {


    private boolean loggedIn = false;
    ImageView selectedImage,zoom;
    TextView nameTxt, brandTxt, colorTxt, sizeTxt;
    Context c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view_withsize_final);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductSizeImageSingleViewFullDetails.this, ProductSizeGridViewImages.class);
                    finish();
                }
            });


            selectedImage = (ImageView) findViewById(R.id.img1); //init a ImageView
        nameTxt = (TextView) findViewById(R.id.nameTxt);
        brandTxt = (TextView) findViewById(R.id.brandTxt);
        sizeTxt = (TextView) findViewById(R.id.sizeTxt);
        colorTxt = (TextView) findViewById(R.id.colorTxt);
zoom=(ImageView)findViewById(R.id.imageButton);
        // Get intent data
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        final int pid = i.getExtras().getInt("PRODUCTID_KEY");
        final int psid = i.getExtras().getInt("PRODUCTSIZEID_KEY");
        final String name = i.getExtras().getString("NAME_KEY");
        final String image = i.getExtras().getString("IMAGE_KEY");
        final String brand = i.getExtras().getString("BRAND_KEY");
        final String color = i.getExtras().getString("COLOR_KEY");
        final String size = i.getExtras().getString("SIZE_KEY");
        nameTxt.setText(name);
        brandTxt.setText(brand);
        colorTxt.setText(color);
        sizeTxt.setText(size);
        Glide.with(this)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
                .centerCrop()
                .crossFade()
                .into(selectedImage);

            selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductSizeImageSingleViewFullDetails.this,ProductSizeSingleViewFullImage.class);
                in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                in.putExtra("IMAGE_KEY",image);
                in.putExtra("PRODUCTID_KEY",pid);
                in.putExtra("PRODUCTSIZEID_KEY",psid);
                in.putExtra("NAME_KEY",name);
                in.putExtra("BRAND_KEY",brand);
                in.putExtra("COLOR_KEY",color);
                in.putExtra("SIZE_KEY",size);
                startActivity(in);
            }
        });


        }
    }
}
