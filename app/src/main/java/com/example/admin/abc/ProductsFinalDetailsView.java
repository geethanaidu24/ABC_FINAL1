package com.example.admin.abc;

/**
 * Created by Geetha on 4/10/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProductsFinalDetailsView extends AppCompatActivity {
    ImageView back;
    //Context c;
    final static String url = "http://192.168.0.3/abc/getProductTypesImages.php?ProductId=";
    //final static String urlAddress = "http://10.0.2.2/abc/getProductTypesImages.php";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types);
       // TextView tx=(TextView)findViewById(R.id.t1);
        final ListView lv = (ListView) findViewById(R.id.productTypesLv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

       // String poid = intent.getExtras().getString("PRODUCTIDPOSITION_KEY");
       // int pTypeId = intent.getExtras().getInt("PRODUCTID_KEY");

     //   int id1 = intent.getExtras().getInt("PRODUCTID_KEY");

      //  Log.d("result position id: ", "> " + poid);
       // Log.d("result product id: ", "> " + id1);


        int pid = intent.getExtras().getInt("PRODUCTID_KEY");
       // String name=intent.getExtras().getString("PRODUCTNAME_KEY").toString();
       Log.d("result PID: ", "> " + pid);

        String urlAddress = url + pid;

        new ProductTypesDownloader(ProductsFinalDetailsView.this,urlAddress,lv).execute();

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductsFinalDetailsView.this,Products.class);
                startActivity(in);
            }
        });
    }
}
