package com.atwyn.android.abc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by Geetha on 4/21/2017.
 */

public class ProductSizeImageSingleViewFullDetails extends AppCompatActivity {
int click=0;
ImageButton zoom;
    private boolean loggedIn = false;
    ImageView selectedImage;
    TextView nameTxt, brandTxt, colorTxt, sizeTxt;
    Context c;
    ArrayList<MySQLDataBase> mySQLDataBases;
    private static int pid,psid,selProLength,selProWidth,selProHeight;
    private static String selProductName,selFinalProSize,name,image,brand,color,size,finalUrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view_withsize_final);
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        pid = i.getExtras().getInt("PRODUCTID_KEY");
        psid = i.getExtras().getInt("PRODUCTSIZEID_KEY");
         name = i.getExtras().getString("NAME_KEY");
        image = i.getExtras().getString("IMAGE_KEY");
        brand = i.getExtras().getString("BRAND_KEY");
         color = i.getExtras().getString("COLOR_KEY");
         size = i.getExtras().getString("SIZE_KEY");
        selProductName = i.getExtras().getString("PRODUCTNAME_KEY");
        selFinalProSize = i.getExtras().getString("FINALPROSELSIZE_KEY");
        selProLength = i.getExtras().getInt("PRODUCTSIZELENGTH_KEY");
        selProWidth = i.getExtras().getInt("PRODUCTSIZEWIDTH_KEY");
        selProHeight = i.getExtras().getInt("PRODUCTSIZEHEIGHT_KEY");
        mySQLDataBases = (ArrayList<MySQLDataBase>) i.getSerializableExtra("ProductSizeList");
       finalUrl=Config.mainUrlAddress + image;
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
                        Intent inn = new Intent(ProductSizeImageSingleViewFullDetails.this, ProductSizeGridViewImages.class);

                        /*inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                        inn.putExtra("PRODUCTID_KEY", pid);
                        inn.putExtra("PRODUCTNAME_KEY", selProductName);
                        inn.putExtra("PRODUCTSIZEID_KEY", psid);
                        inn.putExtra("FINALPROSELSIZE_KEY", selFinalProSize);
                        inn.putExtra("PRODUCTSIZEWIDTH_KEY", selProWidth);
                        inn.putExtra("PRODUCTSIZELENGTH_KEY", selProLength);
                        inn.putExtra("PRODUCTSIZEHEIGHT_KEY", selProHeight);
                        inn.putExtra("ProductSizeList",mySQLDataBases);
                        setResult(Activity.RESULT_OK,inn);
                        startActivity(inn);*/
                        finish();
                        /*startActivity(inn);

                        finish();*/
                }
                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ho);
            toolbar.setOverflowIcon(drawable);
            selectedImage = (ImageView) findViewById(R.id.img1); //init a ImageView
        nameTxt = (TextView) findViewById(R.id.nameTxt);
        brandTxt = (TextView) findViewById(R.id.brandTxt);
        sizeTxt = (TextView) findViewById(R.id.sizeTxt);
        colorTxt = (TextView) findViewById(R.id.colorTxt);
zoom=(ImageButton) findViewById(R.id.imageButton);
        // Get intent data

        nameTxt.setText(name);
        brandTxt.setText(brand);
        colorTxt.setText(color);
        sizeTxt.setText(size);
        Glide.with(this)
                .load(finalUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .centerCrop()
                .crossFade()
                .into(selectedImage);

            zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = click + 1;
                if (click == 1) {
                    click = 0;
                    Intent in = new Intent(ProductSizeImageSingleViewFullDetails.this, ProductSizeSingleViewFullImage.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    in.putExtra("IMAGE_KEY", image);
                    in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTSIZEID_KEY", psid);
                    in.putExtra("NAME_KEY", name);
                    in.putExtra("BRAND_KEY", brand);
                    in.putExtra("COLOR_KEY", color);
                    in.putExtra("SIZE_KEY", size);
                    in.putExtra("ProductSizeList",mySQLDataBases);
                    in.putExtra("PRODUCTNAME_KEY",selProductName);
                    in.putExtra("FINALPROSELSIZE_KEY",selFinalProSize);
                    in.putExtra("PRODUCTSIZELENGTH_KEY",selProLength);
                    in.putExtra("PRODUCTSIZEWIDTH_KEY",selProWidth);
                    in.putExtra("PRODUCTSIZEHEIGHT_KEY",selProHeight);
                    startActivity(in);
                }
            }
        });

        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.



        //getMenuInflater().inflate(R.menu.mainproducts, menu);


        getMenuInflater().inflate(R.menu.home, menu);


        return true;


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==R.id.h1)
        {
            Intent inn = new Intent(ProductSizeImageSingleViewFullDetails.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
               /* inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);*/
            startActivity(inn);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent inn = new Intent(ProductSizeImageSingleViewFullDetails.this, ProductSizeGridViewImages.class);

            /*inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*//*);
            inn.putExtra("PRODUCTID_KEY", pid);
            inn.putExtra("PRODUCTNAME_KEY", selProductName);
            inn.putExtra("PRODUCTSIZEID_KEY", psid);
            inn.putExtra("FINALPROSELSIZE_KEY", selFinalProSize);
            inn.putExtra("PRODUCTSIZEWIDTH_KEY", selProWidth);
            inn.putExtra("PRODUCTSIZELENGTH_KEY", selProLength);
            inn.putExtra("PRODUCTSIZEHEIGHT_KEY", selProHeight);
            inn.putExtra("ProductSizeList",mySQLDataBases);
            setResult(Activity.RESULT_OK,inn);
            startActivity(inn);*/
            finish();
            super.onBackPressed();
        }
    }
}
