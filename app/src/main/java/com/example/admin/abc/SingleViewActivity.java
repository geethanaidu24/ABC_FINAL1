package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Geetha on 4/10/2017.
 */

public class SingleViewActivity extends AppCompatActivity {
    ImageView selectedImage;
    TextView nameTxt, brandTxt, colorTxt;
    Context c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view_final);
        selectedImage = (ImageView) findViewById(R.id.img1) ; //init a ImageView
        nameTxt = (TextView)findViewById(R.id.nameTxt);
        brandTxt = (TextView)findViewById(R.id.brandTxt);
        colorTxt = (TextView)findViewById(R.id.colorTxt);

        // Get intent data
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        String name = i.getExtras().getString("NAME_KEY");
        String image = i.getExtras().getString("IMAGE_KEY");
        String brand = i.getExtras().getString("BRAND_KEY");
        String color = i.getExtras().getString("COLOR_KEY");
        nameTxt.setText(name);
        brandTxt.setText(brand);
        colorTxt.setText(color);
        PicassoClient.downloadImage(c,image,selectedImage);

    }
}
