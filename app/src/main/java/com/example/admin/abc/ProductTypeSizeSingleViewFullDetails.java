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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by Geetha on 4/10/2017.
 */

public class ProductTypeSizeSingleViewFullDetails extends AppCompatActivity {

    ImageView back;
int click=0;
    ImageButton zoom;
    ImageView selectedImage;
    TextView nameTxt, brandTxt, colorTxt, sizeTxt;
    Context c;
    String productType,selectedProductSize,name,color,image,brand,size,pname,finalUrl;
    int selLength,selWidth,selHeight,pid,ptid,ptsid;
    ArrayList<MySQLDataBase> mySQLDataBases1;
    ArrayList<MySQLDataBase> mySQLDataBases2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view_withsize_final);
// Get intent data
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
         pid = i.getExtras().getInt("PRODUCTID_KEY");
         pname =i.getExtras().getString("PRODUCTNAME_KEY");
         ptid = i.getExtras().getInt("PRODUCTTYPEID_KEY");
         ptsid = i.getExtras().getInt("PRODUCTTYPESIZEID_KEY");
         name = i.getExtras().getString("NAME_KEY");
         image = i.getExtras().getString("IMAGE_KEY");
         brand = i.getExtras().getString("BRAND_KEY");
         color = i.getExtras().getString("COLOR_KEY");
         size = i.getExtras().getString("SIZE_KEY");
        productType = i.getExtras().getString("PRODUCTTYPE_KEY");
        selectedProductSize = i.getExtras().getString("FINALSIZE_KEY");
        selLength = i.getExtras().getInt("LENGTH_KEY");
        selWidth = i.getExtras().getInt("WIDTH_KEY");
        selHeight = i.getExtras().getInt("HEIGHT_KEY");
        mySQLDataBases2 = (ArrayList<MySQLDataBase>) i.getSerializableExtra("ProductTypeSizeList");
        mySQLDataBases1 = (ArrayList<MySQLDataBase>) i.getSerializableExtra("ProductTypeList");
        finalUrl = Config.mainUrlAddress + image;
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
                        Intent i = new Intent(ProductTypeSizeSingleViewFullDetails.this, ProductTypeSizeImagesGridView.class);

                       /* i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
                        i.putExtra("PRODUCTID_KEY", pid);
                        i.putExtra("PRODUCTNAME_KEY", pname);
                        i.putExtra("PRODUCTTYPEID_KEY", ptid);
                        i.putExtra("PRODUCTTYPESIZEID_KEY", ptsid);
                        i.putExtra("FINALSIZE_KEY",selectedProductSize);
                        i.putExtra("LENGTH_KEY",selLength);
                        i.putExtra("WIDTH_KEY",selWidth);
                        i.putExtra("HEIGHT_KEY",selHeight);
                        i.putExtra("PRODUCTTYPE_KEY", productType);
                        i.putExtra("ProductTypeSizeList",mySQLDataBases2);
                        i.putExtra("ProductTypeList",mySQLDataBases1);
                        setResult(Activity.RESULT_OK,i);
                        startActivity(i);*/
                        finish();
                    }

                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ho);
            toolbar.setOverflowIcon(drawable);

            selectedImage = (ImageView) findViewById(R.id.img1) ; //init a ImageView
        nameTxt = (TextView)findViewById(R.id.nameTxt);
        brandTxt = (TextView)findViewById(R.id.brandTxt);
        sizeTxt = (TextView)findViewById(R.id.colorTxt);
        colorTxt = (TextView)findViewById(R.id.sizeTxt);
            zoom=(ImageButton)findViewById(R.id.imageButton) ;

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
       //PicassoClient.downloadImage(c,image,selectedImage);

        zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(ProductTypeSizeSingleViewFullDetails.this, SingleViewImageFull.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    in.putExtra("IMAGE_KEY", image);
                    in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY", pname);
                    in.putExtra("PRODUCTTYPEID_KEY", ptid);
                    in.putExtra("PRODUCTTYPESIZEID_KEY", ptsid);
                    in.putExtra("NAME_KEY", name);
                    in.putExtra("BRAND_KEY", brand);
                    in.putExtra("COLOR_KEY", color);
                    in.putExtra("SIZE_KEY",size);
                    in.putExtra("PRODUCTTYPE_KEY",productType);
                    in.putExtra("FINALSIZE_KEY",selectedProductSize);
                    in.putExtra("LENGTH_KEY",selLength);
                    in.putExtra("WIDTH_KEY",selWidth);
                    in.putExtra("HEIGHT_KEY",selHeight);
                    in.putExtra("ProductTypeList",mySQLDataBases1);
                    in.putExtra("ProductTypeSizeList",mySQLDataBases2);
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
            Intent inn = new Intent(ProductTypeSizeSingleViewFullDetails.this, Main2Activity.class);
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
            Intent i = new Intent(ProductTypeSizeSingleViewFullDetails.this, ProductTypeSizeImagesGridView.class);
            /*i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK *//*|
                                Intent.FLAG_ACTIVITY_NEW_TASK*//*);
            i.putExtra("PRODUCTID_KEY", pid);
            i.putExtra("PRODUCTNAME_KEY", pname);
            i.putExtra("PRODUCTTYPEID_KEY", ptid);
            i.putExtra("PRODUCTTYPESIZEID_KEY", ptsid);
            i.putExtra("FINALSIZE_KEY",selectedProductSize);
            i.putExtra("LENGTH_KEY",selLength);
            i.putExtra("WIDTH_KEY",selWidth);
            i.putExtra("HEIGHT_KEY",selHeight);
            i.putExtra("PRODUCTTYPE_KEY", productType);
            i.putExtra("ProductTypeSizeList",mySQLDataBases2);
            i.putExtra("ProductTypeList",mySQLDataBases1);
            setResult(Activity.RESULT_OK,i);
            startActivity(i);*/
            finish();
super.onBackPressed();

        }
    }
}
