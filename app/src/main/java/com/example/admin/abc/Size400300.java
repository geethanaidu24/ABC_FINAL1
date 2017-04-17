package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Size400300 extends AppCompatActivity {
ImageView im1;
    ImageView im2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size400300);
        im1=(ImageView) findViewById(R.id.im61);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Size400300.this,Size400300full.class);
                startActivity(in);
            }
        });
        im2=(ImageView)findViewById(R.id.back);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Size400300.this,Basins.class);
                startActivity(in);
            }
        });
    }
}
