package com.example.admin.abc;

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

/**
 * Created by Geetha on 4/24/2017.
 */

public class ProductTypeSingleImageFullViewActivity extends AppCompatActivity {

    ImageView back;

    ImageView selectedImage;
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
        final int ptid = i.getExtras().getInt("PRODUCTTYPEID_KEY");
        final int pid = i.getExtras().getInt("PRODUCTID_KEY");
        final String pname=i.getExtras().getString("PRODUCTNAME_KEY");
        final String name = i.getExtras().getString("NAME_KEY");
        final String image = i.getExtras().getString("IMAGE_KEY");
        final String brand = i.getExtras().getString("BRAND_KEY");
        final String color = i.getExtras().getString("COLOR_KEY");
        Glide.with(this)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
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
                        Intent in = new Intent(ProductTypeSingleImageFullViewActivity.this, ProductTypesGridView.class);

                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
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
            Intent in = new Intent(ProductTypeSingleImageFullViewActivity.this, ProductTypesGridView.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }


    }
}
