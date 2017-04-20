package com.example.admin.abc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Geetha on 4/18/2017 for opening size typed images.
 */

public class ProductTypeSizeImages extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = "http://192.168.0.2/abc/getProductTypeSizeImages.php?SizeId=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_sizes_images);

        final GridView gv = (GridView) findViewById(R.id.gv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        int psid = intent.getExtras().getInt("PRODUCTTYPESIZEID_KEY");

          String urlAddress = url + psid;


        new ProductTypeSizeImagesDownloader(ProductTypeSizeImages.this,urlAddress,gv).execute();

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductTypeSizeImages.this,Products.class);
                startActivity(in);
            }
        });
    }
}