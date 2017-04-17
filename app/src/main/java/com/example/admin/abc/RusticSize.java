package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RusticSize extends AppCompatActivity {
ImageView b1;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rustic_size);

        b1=(ImageView)findViewById(R.id.back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RusticSize.this,Surfaces.class);
                startActivity(in);
            }
        });

        bt=(Button)findViewById(R.id.buttonws);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RusticSize.this,RusticSizeTiles.class);
                startActivity(in);
            }
        });
    }
}
