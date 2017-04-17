package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RusticSizeOne extends AppCompatActivity {
ImageView b3;
   ImageView b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rustic_size_one);
        b3=(ImageView)findViewById(R.id.back);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RusticSizeOne.this,RusticSize.class);
                startActivity(in);
            }
        });

        b4=(ImageView) findViewById(R.id.im61);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RusticSizeOne.this,RusticSizeOneFull.class);
                startActivity(in);
            }
        });

    }
}
