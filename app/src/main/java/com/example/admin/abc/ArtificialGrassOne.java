package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ArtificialGrassOne extends AppCompatActivity {
ImageView aig1,aig2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artificial_grass_one);

       aig1=(ImageView) findViewById(R.id.im61);
        aig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ArtificialGrassOne.this,ArtificialGrassOneFull.class);
                startActivity(in);
            }
        });
       aig2=(ImageView)findViewById(R.id.back);
        aig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ArtificialGrassOne.this,ArtificialGrass.class);
                startActivity(in);
            }
        });
    }
}
