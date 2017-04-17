package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WoodenSize extends AppCompatActivity {
    ImageView b19;
    Button b20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wooden_size);
        b19=(ImageView)findViewById(R.id.back);
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(WoodenSize.this,Surfaces.class);
                startActivity(in);
            }
        });

        b20=(Button)findViewById(R.id.buttonws);
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(WoodenSize.this,RusticSizeTiles.class);
                startActivity(in);
            }
        });
    }
}
