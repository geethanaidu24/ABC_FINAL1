package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MetallicSize extends AppCompatActivity {
    ImageView b15;
    Button b16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metallic_size);
        b15=(ImageView)findViewById(R.id.back);
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MetallicSize.this,Surfaces.class);
                startActivity(in);
            }
        });

        b16=(Button)findViewById(R.id.buttonws);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MetallicSize.this,RusticSizeTiles.class);
                startActivity(in);
            }
        });
    }
}