package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductSizeSingleViewActivity extends AppCompatActivity {

    ImageView back;

    ImageView selectedImage;
    TextView nameTxt, brandTxt, colorTxt, sizeTxt;
    Context c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view_withsize_final);
        selectedImage = (ImageView) findViewById(R.id.img1); //init a ImageView
        nameTxt = (TextView) findViewById(R.id.nameTxt);
        brandTxt = (TextView) findViewById(R.id.brandTxt);
        sizeTxt = (TextView) findViewById(R.id.sizeTxt);
        colorTxt = (TextView) findViewById(R.id.colorTxt);

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
        PicassoClient.downloadImage(c, image, selectedImage);
       /* back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductSizeSingleViewActivity.this,ProductSizeImages.class);
                in.putExtra("PRODUCTID_KEY",pid);
                in.putExtra("PRODUCTSIZEID_KEY", psid);
                in.putExtra("PRODUCTSIZE_KEY", size);
                startActivity(in);
            }
        });*/

        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductSizeSingleViewActivity.this,ProductSizeSingleViewFullImageActivity.class);
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

        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductSizeSingleViewActivity.this, ProductSizeImages.class);
                   /* in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTSIZEID_KEY", psid);
                    in.putExtra("PRODUCTSIZE_KEY", size);
                    startActivity(in);*/
                   finish();
                }
            });


        }
    }
}
