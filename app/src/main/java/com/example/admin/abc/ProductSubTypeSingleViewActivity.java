package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductSubTypeSingleViewActivity extends AppCompatActivity {

    ImageView back;
int click=0;
    ImageView img;
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
                    Intent in = new Intent(ProductSubTypeSingleViewActivity.this, ProductSubTypeGridView.class);
                    finish();
                }
            });



        img = (ImageView) findViewById(R.id.img1); //init a ImageView
        nameTxt = (TextView) findViewById(R.id.nameTxt);
        brandTxt = (TextView) findViewById(R.id.brandTxt);
        colorTxt = (TextView) findViewById(R.id.colorTxt);

        // Get intent data
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        final int pstid = i.getExtras().getInt("PRODUCTSUBTYPEID_KEY");
        final String name = i.getExtras().getString("NAME_KEY");
        final String image = i.getExtras().getString("IMAGE_KEY");
        final String brand = i.getExtras().getString("BRAND_KEY");
        final String color = i.getExtras().getString("COLOR_KEY");
        nameTxt.setText(name);
        brandTxt.setText(brand);
        colorTxt.setText(color);
        Log.d("selected img url",""+image);
        final String finImgUrl = Config.mainUrlAddress+image;
        Log.d("selected img url",""+finImgUrl);
        Glide.with(this)
                .load(finImgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
                .centerCrop()
                .crossFade()
                .into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(ProductSubTypeSingleViewActivity.this, ProductSubTypeSingleViewImageFull.class);
                    //in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    in.putExtra("IMAGE_KEY", finImgUrl);
                    in.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
                    in.putExtra("NAME_KEY", name);
                    in.putExtra("BRAND_KEY", brand);
                    in.putExtra("COLOR_KEY", color);
                    startActivity(in);
                }
            }
        });

        }

    }
    public void onBackPressed() {
        //finishAffinity();
        Intent in = new Intent(ProductSubTypeSingleViewActivity.this, ProductSubTypeGridView.class);


        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();



    }
}
