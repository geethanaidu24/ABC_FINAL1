package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TubsSize extends AppCompatActivity {
    ImageView b8;
    Button b9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tubs_size);
        b8=(ImageView)findViewById(R.id.back);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(TubsSize.this,Bathroom.class);
                startActivity(in);
            }
        });
        b9=(Button)findViewById(R.id.buttonws);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(TubsSize.this,Basins.class);
                startActivity(in);
            }
        });

    }

}