package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PaintOne extends AppCompatActivity {
ImageView ip1,ip2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_one);
        ip1=(ImageView)findViewById(R.id.back);
        ip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(PaintOne.this,PaintsTypes.class);
                startActivity(in);
            }
        });

        ip2=(ImageView)findViewById(R.id.im61);
        ip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(PaintOne.this,PaintsOneFull.class);
                startActivity(in);
            }
        });
    }
}
