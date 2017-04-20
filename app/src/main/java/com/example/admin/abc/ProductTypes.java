package com.example.admin.abc;

/**
 * Created by Geetha on 4/10/2017 for opening Product types activity based on user clicked product .
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProductTypes extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = "http://192.168.0.4/abc/getProductTypesImages.php?ProductId=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types);

        final ListView lv = (ListView) findViewById(R.id.productTypesLv);
        TextView typeNameTxt= (TextView) findViewById(R.id.SelProductName);


        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        int pid = intent.getExtras().getInt("PRODUCTID_KEY");
        String name = intent.getExtras().getString("PRODUCTNAME_KEY");
        typeNameTxt.setText(name);
        Log.d("result PID: ", "> " + pid);

        String urlAddress = url + pid;

        new ProductTypesDownloader(ProductTypes.this,urlAddress,lv,pid).execute();

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductTypes.this,Products.class);
                startActivity(in);
            }
        });
    }
}
