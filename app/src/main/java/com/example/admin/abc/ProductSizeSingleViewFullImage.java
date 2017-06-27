package com.example.admin.abc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class ProductSizeSingleViewFullImage extends AppCompatActivity {
    ImageView im;
    Context c;
    ArrayList<MySQLDataBase> mySQLDataBases;
    private static int pid,psid,selProLength,selProWidth,selProHeight;
    private static String selProductName,selFinalProSize,image,name,brand,color,size,finalUrl;
int click=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_size_single_view_full_image);

            im = (ImageView) findViewById(R.id.fullimage);

        Intent i = this.getIntent(); // get Intent which we set from Previous Activity


        selProductName = i.getExtras().getString("PRODUCTNAME_KEY");
        selFinalProSize = i.getExtras().getString("FINALPROSELSIZE_KEY");
        selProLength = i.getExtras().getInt("PRODUCTSIZELENGTH_KEY");
        selProWidth = i.getExtras().getInt("PRODUCTSIZEWIDTH_KEY");
        selProHeight = i.getExtras().getInt("PRODUCTSIZEHEIGHT_KEY");
        mySQLDataBases = (ArrayList<MySQLDataBase>) i.getSerializableExtra("ProductSizeList");
             image = i.getExtras().getString("IMAGE_KEY");
             pid = i.getExtras().getInt("PRODUCTID_KEY");
            psid = i.getExtras().getInt("PRODUCTSIZEID_KEY");
           name = i.getExtras().getString("NAME_KEY");
             brand = i.getExtras().getString("BRAND_KEY");
             color = i.getExtras().getString("COLOR_KEY");
            size = i.getExtras().getString("SIZE_KEY");
        finalUrl=Config.mainUrlAddress + image;
        Glide.with(this)
                .load(finalUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
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
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        Intent in = new Intent(ProductSizeSingleViewFullImage.this, ProductSizeImageSingleViewFullDetails.class);

                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*/);
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
                        setResult(Activity.RESULT_OK,in);
                        startActivity(in);
                        finish();
                    }
                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ho);
            toolbar.setOverflowIcon(drawable);

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
            Intent inn = new Intent(ProductSizeSingleViewFullImage.this, Main2Activity.class);
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
        click = click + 1;
        if (click == 1) {
            click = 0;
            //finishAffinity();
            Intent in = new Intent(ProductSizeSingleViewFullImage.this, ProductSizeImageSingleViewFullDetails.class);

            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                    Intent.FLAG_ACTIVITY_NEW_TASK*/);
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
            setResult(Activity.RESULT_OK,in);
            startActivity(in);
            finish();
            super.onBackPressed();
        }



    }
}
