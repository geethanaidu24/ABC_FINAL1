package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CeramicTilesSizeOneFull extends AppCompatActivity {
ImageView imc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceramic_tiles_size_one_full);

        imc=(ImageView)findViewById(R.id.back);

        imc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(CeramicTilesSizeOneFull.this,CeramicTilesSizeOne.class);
                startActivity(in);
            }
        });
    }
}
