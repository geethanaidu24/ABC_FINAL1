package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class CeramicTiles extends AppCompatActivity {
ImageView ima;
 Button b8;

    GridView androidGridView;

    String[] gridViewString = {
            "Amron Beige", "Amron Brown", "California Black", "Daino Beige", "Helix Brown", "Lavante Bianco", "Signature Brown","X8MFU Cicilia"

    } ;
    int[] gridViewImageId = {
            R.mipmap.ctilesone, R.mipmap.ctilestwo, R.mipmap.ctilesthree, R.mipmap.ctilesfour, R.mipmap.ctilesfive, R.mipmap.ctilessix, R.mipmap.ctilesseven,R.mipmap.ctileseight

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceramic_tiles);
        ima=(ImageView)findViewById(R.id.back);
        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(CeramicTiles.this,CeramicTilesSize.class);
                startActivity(in);
            }
        });



        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(CeramicTiles.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
               Intent in=new Intent(CeramicTiles.this,CeramicTilesSizeOne.class);
                startActivity(in);
            }
        });

    }
}
