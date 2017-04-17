package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GVTSize extends AppCompatActivity {
    ImageView b17;
    Button b18;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvtsize);
        b17=(ImageView)findViewById(R.id.back);
        b17.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent in=new Intent(GVTSize.this,Surfaces.class);
        startActivity(in);
        }
        });

        b18=(Button)findViewById(R.id.buttonws);
        b18.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent in=new Intent(GVTSize.this,RusticSizeTiles.class);
        startActivity(in);
        }
        });
        }
        }