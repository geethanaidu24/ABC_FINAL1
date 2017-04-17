package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UrinalSize extends AppCompatActivity {
    ImageView b10;
    Button b11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urinal_size);
        b10=(ImageView)findViewById(R.id.back);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(UrinalSize.this,Bathroom.class);
                startActivity(in);
            }
        });
        b11=(Button)findViewById(R.id.buttonws);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(UrinalSize.this,Basins.class);
                startActivity(in);
            }
        });

    }

}