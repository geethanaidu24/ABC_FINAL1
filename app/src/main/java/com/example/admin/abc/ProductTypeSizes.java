package com.example.admin.abc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Geetha on 4/14/2017 for opening Product Types Size activity based on user clicked product .
 */

public class ProductTypeSizes extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = "http://192.168.0.6/abc/getProductTypeSizes.php?ProductTypeId=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_sizes);

        final ListView lv = (ListView) findViewById(R.id.productTypeSizesLv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        Log.d("result PID: ", "> " + ptid);

        String urlAddress = url + ptid;


        new ProductTypeSizesDownloader(ProductTypeSizes.this,urlAddress,lv).execute();

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductTypeSizes.this,ProductTypes.class);
                startActivity(in);
            }
        });
    }

}
