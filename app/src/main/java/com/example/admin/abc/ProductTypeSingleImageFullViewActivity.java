package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Geetha on 4/24/2017.
 */

public class ProductTypeSingleImageFullViewActivity extends AppCompatActivity {

    ImageView back;

    ImageView selectedImage;

    Context c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_size_single_view_full_image);
        selectedImage = (ImageView) findViewById(R.id.fullimage) ; //init a ImageView


        // Get intent data
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        final int ptid = i.getExtras().getInt("PRODUCTTYPEID_KEY");
        final int pid = i.getExtras().getInt("PRODUCTID_KEY");
        final String pname=i.getExtras().getString("PRODUCTNAME_KEY");
        final String name = i.getExtras().getString("NAME_KEY");
        final String image = i.getExtras().getString("IMAGE_KEY");
        final String brand = i.getExtras().getString("BRAND_KEY");
        final String color = i.getExtras().getString("COLOR_KEY");

        PicassoClient.downloadImage(c,image,selectedImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ProductTypeSingleImageFullViewActivity.this,ProductTypeImages.class);
                    /*in.putExtra("PRODUCTID_KEY",pid);
                    in.putExtra("PRODUCTNAME_KEY",pname);
                    in.putExtra("PRODUCTTYPEID_KEY",ptid);
                    in.putExtra("NAME_KEY",name);
                    in.putExtra("IMAGE_KEY",image);
                    in.putExtra("BRAND_KEY",brand);
                    in.putExtra("COLOR_KEY",color);
                    startActivity(in);*/
                    finish();
                }
            });

            // Inflate a menu to be displayed in the toolbar
            //  actionbar.inflateMenu(R.menu.actions);
        }
        /*back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductTypeSingleViewActivity.this,ProductTypeImages.class);
                in.putExtra("PRODUCTID_KEY", pid);
                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                startActivity(in);
            }
        });*/

    }
}
