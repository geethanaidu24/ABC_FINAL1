package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class RusticSizeTiles extends AppCompatActivity {
ImageView imr;
  Button br;

    GridView androidGridView;

    String[] gridViewString = {
            "Signature Brown", "Amron Brown", "California Black", "Daino Beige", "Helix Brown", "Lavante Bianco", "Amron Beige","X8MFU Cicilia"

    } ;
    int[] gridViewImageId = {
            R.mipmap.ctilesseven, R.mipmap.ctilestwo, R.mipmap.ctilesthree, R.mipmap.ctilesfour, R.mipmap.ctilesfive, R.mipmap.ctilessix, R.mipmap.ctilesone,R.mipmap.ctileseight

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rustic_size_tiles);
        imr=(ImageView)findViewById(R.id.back);

        imr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RusticSizeTiles.this,RusticSize.class);
                startActivity(in);
            }
        });



        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(RusticSizeTiles.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Intent in=new Intent(RusticSizeTiles.this,RusticSizeOne.class);
                startActivity(in);
            }
        });

    }
}
