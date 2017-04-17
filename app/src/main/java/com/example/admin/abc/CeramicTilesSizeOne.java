package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CeramicTilesSizeOne extends AppCompatActivity {
ImageView im11,im12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceramic_tiles_size_one);

        im11=(ImageView)findViewById(R.id.back);
        im12=(ImageView)findViewById(R.id.im61);

        im11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(CeramicTilesSizeOne.this,CeramicTiles.class);
                startActivity(in);
            }
        });

        im12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(CeramicTilesSizeOne.this,CeramicTilesSizeOneFull.class);
                startActivity(in);
            }
        });

    }
}
