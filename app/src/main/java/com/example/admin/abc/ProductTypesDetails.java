package com.example.admin.abc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Atwyn on 4/12/2017.
 */

public class ProductTypesDetails extends AppCompatActivity {
    ImageView back;

    // @Written By Geetha - Code Starts Here

    final static String urlAddress ="http://192.168.0.2/abc/getProductImages.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types);

      //  final ListView lv = (ListView) findViewById(R.id.lv1);

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductTypesDetails.this,Main2Activity.class);
                startActivity(in);
            }
        });


    }
}
