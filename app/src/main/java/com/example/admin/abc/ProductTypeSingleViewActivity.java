package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductTypeSingleViewActivity extends AppCompatActivity {

    ImageView back;
int click=0;
    ImageButton zoom;
    ImageView selectedImage;
    TextView nameTxt, brandTxt, colorTxt;
    Context c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
      //  getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view_final);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        Intent in = new Intent(ProductTypeSingleViewActivity.this, ProductTypesGridView.class);
                    /*in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY",pname);
                    in.putExtra("PRODUCTTYPEID_KEY",ptid);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);*/
                        finish();
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }
            });



            selectedImage = (ImageView) findViewById(R.id.img1) ; //init a ImageView
        nameTxt = (TextView)findViewById(R.id.nameTxt);
        brandTxt = (TextView)findViewById(R.id.brandTxt);
        colorTxt = (TextView)findViewById(R.id.colorTxt);
            zoom=(ImageButton)findViewById(R.id.imageButton2);

        // Get intent data
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        final int ptid = i.getExtras().getInt("PRODUCTTYPEID_KEY");
        final int pid = i.getExtras().getInt("PRODUCTID_KEY");
        final String pname=i.getExtras().getString("PRODUCTNAME_KEY");
        final String name = i.getExtras().getString("NAME_KEY");
        final String image = i.getExtras().getString("IMAGE_KEY");
        final String brand = i.getExtras().getString("BRAND_KEY");
        final String color = i.getExtras().getString("COLOR_KEY");
        nameTxt.setText(name);
        brandTxt.setText(brand);
        colorTxt.setText(color);
        Glide.with(this)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
                .centerCrop()
                .crossFade()
                .into(selectedImage);

        zoom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(ProductTypeSingleViewActivity.this, ProductTypeSingleImageFullViewActivity.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY", pname);
                    in.putExtra("PRODUCTTYPEID_KEY", ptid);
                    in.putExtra("NAME_KEY", name);
                    in.putExtra("IMAGE_KEY", image);
                    in.putExtra("BRAND_KEY", brand);
                    in.putExtra("COLOR_KEY", color);
                    startActivity(in);
                }

            }
        });


            // Inflate a menu to be displayed in the toolbar
            //  actionbar.inflateMenu(R.menu.actions);
        }

    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(ProductTypeSingleViewActivity.this, ProductTypesGridView.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }



    }
}
