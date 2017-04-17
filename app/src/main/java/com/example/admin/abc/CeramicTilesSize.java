package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CeramicTilesSize extends AppCompatActivity {
ImageView i;
    Button b45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceramic_tiles_size);

i=(ImageView)findViewById(R.id.back);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(CeramicTilesSize.this,Products.class);
                startActivity(in);
            }
        });


        b45=(Button) findViewById(R.id.buttonws);
        b45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(CeramicTilesSize.this,CeramicTiles.class);
                startActivity(in);
            }
        });
    }
}
