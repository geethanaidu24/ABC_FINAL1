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

public class ProductSubTypeSingleViewActivity extends AppCompatActivity {

    ImageView back;

    ImageView selectedImage;
    TextView nameTxt, brandTxt, colorTxt;
    Context c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view_final);


        selectedImage = (ImageView) findViewById(R.id.img1); //init a ImageView
        nameTxt = (TextView) findViewById(R.id.nameTxt);
        brandTxt = (TextView) findViewById(R.id.brandTxt);
        colorTxt = (TextView) findViewById(R.id.colorTxt);

        // Get intent data
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        final int pid = i.getExtras().getInt("PRODUCTID_KEY");
        final String pname = i.getExtras().getString("PRODUCTNAME_KEY");
        final int ptid = i.getExtras().getInt("PRODUCTTYPEID_KEY");
        final String ptname = i.getExtras().getString("PRODUCTTYPENAME_KEY");
        final int pstid = i.getExtras().getInt("PRODUCTSUBTYPEID_KEY");
        final String name = i.getExtras().getString("NAME_KEY");
        final String image = i.getExtras().getString("IMAGE_KEY");
        final String brand = i.getExtras().getString("BRAND_KEY");
        final String color = i.getExtras().getString("COLOR_KEY");
        nameTxt.setText(name);
        brandTxt.setText(brand);
        colorTxt.setText(color);
        PicassoClient.downloadImage(c, image, selectedImage);
       /* back = (ImageView) findViewById(R.id.back);
      back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ProductSubTypeSingleViewActivity.this, ProductTypeSubTypeImages.class);
                in.putExtra("PRODUCTID_KEY",pid);
                in.putExtra("PRODUCTNAME_KEY",pname);
                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                in.putExtra("PRODUCTTYPENAME_KEY",ptname);
                in.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
                startActivity(in);
            }
        });*/

        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductSubTypeSingleViewActivity.this,ProductSubTypeSingleViewImageFull.class);
                in.putExtra("IMAGE_KEY",image);
                in.putExtra("PRODUCTID_KEY",pid);
                in.putExtra("PRODUCTNAME_KEY",pname);
                in.putExtra("PRODUCTTYPEID_KEY",ptid);
                in.putExtra("PRODUCTTYPENAME_KEY",ptname);
                in.putExtra("PRODUCTSUBTYPEID_KEY",pstid);
                in.putExtra("NAME_KEY",name);
                in.putExtra("BRAND_KEY",brand);
                in.putExtra("COLOR_KEY",color);
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
                    Intent in = new Intent(ProductSubTypeSingleViewActivity.this, ProductSubTypeGridView.class);
                    in.putExtra("PRODUCTID_KEY", pid);
                    in.putExtra("PRODUCTNAME_KEY", pname);
                    in.putExtra("PRODUCTTYPEID_KEY", ptid);
                    in.putExtra("PRODUCTTYPENAME_KEY", ptname);
                    in.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
                    startActivity(in);
                }
            });



        }
    }
}
