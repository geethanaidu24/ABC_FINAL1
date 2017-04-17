package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class ArtificialGrass extends AppCompatActivity {
   ImageView ai;
    GridView androidGridView;

    String[] gridViewString = {
            "Garden Grass", "Indoor Turf", "Residence Grass", "Synthetic Grass", "Terrace Grass"
    } ;
    int[] gridViewImageId = {
            R.mipmap.agone, R.mipmap.agtwo, R.mipmap.agthree, R.mipmap.agone, R.mipmap.agtwo

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artificial_grass);

        ai=(ImageView)findViewById(R.id.back);
        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ArtificialGrass.this,Decoratives.class);
                startActivity(in);
            }
        });
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(ArtificialGrass.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Intent in = new Intent(ArtificialGrass.this,ArtificialGrassOne.class);
                startActivity(in);
            }
        });
    }
}
