package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Products extends AppCompatActivity {

    ImageView back;


    final static String urlAddress = "http://192.168.0.3/abc/getProductImages.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Products.this,Main2Activity.class);
                startActivity(in);
            }
        });

        final ListView lv = (ListView) findViewById(R.id.productLv);

        new ProductsDownloader(Products.this,urlAddress,lv).execute();


/*
        b1=(ImageView)findViewById(R.id.im61);
        b2=(ImageView)findViewById(R.id.im32);
        b3=(ImageView)findViewById(R.id.im33);
        b4=(ImageView)findViewById(R.id.im34);
        b5=(ImageView)findViewById(R.id.im35);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Products.this,Bathroom.class);
                startActivity(in);
            }
        });


    b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in=new Intent(Products.this,CeramicTilesSize.class);
                startActivity(in);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Products.this,Surfaces.class);
                startActivity(in);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Products.this,Paints.class);
                startActivity(in);

            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Products.this,Decoratives.class);
                startActivity(in);

            }
        });*/




    }
}
