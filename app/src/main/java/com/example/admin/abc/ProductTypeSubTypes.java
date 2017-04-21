package com.example.admin.abc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Geetha on 4/20/2017 for displaying sub menu for product types of main products.
 */

public class ProductTypeSubTypes extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = "http://192.168.0.2/abc/getProductTypeSubTypes.php?ProductTypeId=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_subtypes);

        final ListView lv = (ListView) findViewById(R.id.productTypeSubTypesLv);
        TextView typeNameTxt= (TextView) findViewById(R.id.SelProductTypeName);


        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

      final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
      final int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
      final String ptname = intent.getExtras().getString("PRODUCTTYPENAME_KEY");
        typeNameTxt.setText(ptname);
        Log.d("result PID: ", "> " + pid);
        Log.d("result PTID: ", "> " + ptid);

        String urlAddress = url + ptid;

        new ProductTypeSubTypesDownloader(ProductTypeSubTypes.this,urlAddress,lv,ptid,pid).execute();

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductTypeSubTypes.this,ProductTypes.class);
                in.putExtra("PRODUCTID_KEY",pid);
                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                in.putExtra("PRODUCTTYPENAME_KEY",ptname);
                startActivity(in);
            }
        });
    }

}
