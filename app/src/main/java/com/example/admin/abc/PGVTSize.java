package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PGVTSize extends AppCompatActivity {
ImageView b13;
    Button b14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgvtsize);
        b13=(ImageView)findViewById(R.id.back);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(PGVTSize.this,Surfaces.class);
                startActivity(in);
            }
        });

        b14=(Button)findViewById(R.id.buttonws);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(PGVTSize.this,RusticSizeTiles.class);
                startActivity(in);
            }
        });
    }
}