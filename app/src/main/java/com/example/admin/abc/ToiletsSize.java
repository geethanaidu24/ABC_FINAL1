package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ToiletsSize extends AppCompatActivity {
ImageView b6;
    Button b7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilets_size);
        b6=(ImageView)findViewById(R.id.back);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(ToiletsSize.this,Bathroom.class);
                startActivity(in);
            }
        });
        b7=(Button)findViewById(R.id.buttonws);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(ToiletsSize.this,Basins.class);
                startActivity(in);
            }
        });

    }

}
