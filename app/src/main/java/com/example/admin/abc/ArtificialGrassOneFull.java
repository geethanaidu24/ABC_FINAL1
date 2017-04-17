package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ArtificialGrassOneFull extends AppCompatActivity {
ImageView bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artificial_grass_one_full);
        bg=(ImageView)findViewById(R.id.back);

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(ArtificialGrassOneFull.this,ArtificialGrassOne.class);
                startActivity(in);
            }
        });
    }
}
