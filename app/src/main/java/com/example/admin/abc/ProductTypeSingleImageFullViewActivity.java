package com.example.admin.abc;

import android.app.Activity;
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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by Geetha on 4/24/2017.
 */

public class ProductTypeSingleImageFullViewActivity extends AppCompatActivity {

    ImageView back;
    ImageView selectedImage;
    private int pid,ptid;
    private String pname,ptype,name,image,brand,color,finalUrl;
    ArrayList<MySQLDataBase> mySQLDataBases2;
    ArrayList<MySQLDataBase> mySQLDataBases1;
int click=0;
    Context c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_size_single_view_full_image);
        selectedImage = (ImageView) findViewById(R.id.fullimage) ; //init a ImageView
        // Get intent data
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
         ptid = i.getExtras().getInt("PRODUCTTYPEID_KEY");
         pid = i.getExtras().getInt("PRODUCTID_KEY");
         pname=i.getExtras().getString("PRODUCTNAME_KEY");
         name = i.getExtras().getString("NAME_KEY");
        image = i.getExtras().getString("IMAGE_KEY");
         brand = i.getExtras().getString("BRAND_KEY");
         color = i.getExtras().getString("COLOR_KEY");
       ptype = i.getExtras().getString("PRODUCTTYPE_KEY");
        mySQLDataBases2 = (ArrayList<MySQLDataBase>) i.getSerializableExtra("ProductTypeGridList");

        mySQLDataBases1 = (ArrayList<MySQLDataBase>) i.getSerializableExtra("ProductTypeList");
        finalUrl = Config.mainUrlAddress + image;
        Glide.with(this)
                .load(finalUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .centerCrop()
                .crossFade()
                .into(selectedImage);
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
                        Intent in = new Intent(ProductTypeSingleImageFullViewActivity.this, ProductTypeSingleViewActivity.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*/);
                        in.putExtra("PRODUCTID_KEY", pid);
                        in.putExtra("PRODUCTNAME_KEY", pname);
                        in.putExtra("PRODUCTTYPEID_KEY", ptid);
                        in.putExtra("NAME_KEY", name);
                        in.putExtra("IMAGE_KEY", image);
                        in.putExtra("BRAND_KEY", brand);
                        in.putExtra("COLOR_KEY", color);
                        in.putExtra("PRODUCTTYPE_KEY", ptype);
                        in.putExtra("ProductTypeGridList",mySQLDataBases2);
                        in.putExtra("ProductTypeList",mySQLDataBases1);
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
            Intent inn = new Intent(ProductTypeSingleImageFullViewActivity.this, Main2Activity.class);
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
            Intent in = new Intent(ProductTypeSingleImageFullViewActivity.this, ProductTypeSingleViewActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK /*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*/);
            in.putExtra("PRODUCTID_KEY", pid);
            in.putExtra("PRODUCTNAME_KEY", pname);
            in.putExtra("PRODUCTTYPEID_KEY", ptid);
            in.putExtra("NAME_KEY", name);
            in.putExtra("IMAGE_KEY", image);
            in.putExtra("BRAND_KEY", brand);
            in.putExtra("COLOR_KEY", color);
            in.putExtra("PRODUCTTYPE_KEY", ptype);
            in.putExtra("ProductTypeGridList",mySQLDataBases2);
            in.putExtra("ProductTypeList",mySQLDataBases1);
            setResult(Activity.RESULT_OK,in);
            startActivity(in);
            finish();
            super.onBackPressed();
        }


    }
}
