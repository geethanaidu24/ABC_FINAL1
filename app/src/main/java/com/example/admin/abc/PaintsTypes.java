package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class PaintsTypes extends AppCompatActivity {
ImageView ip;
    Button bp;

    GridView androidGridView;

    String[] gridViewString = {
            "Lady Glaze", "Fernomastic", "Emulsion", "Fer Enamal", "Effects Metallic", "Stain Resistant", "Effects Pearl","Effects Stucco"

    } ;
    int[] gridViewImageId = {
            R.mipmap.paintone, R.mipmap.paintthree, R.mipmap.paintfour, R.mipmap.paintfive, R.mipmap.paintsix, R.mipmap.paintseven, R.mipmap.painteight,R.mipmap.paintone

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paints_types);
        ip=(ImageView)findViewById(R.id.back);


        ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(PaintsTypes.this,Paints.class);
                startActivity(in);
            }
        });

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(PaintsTypes.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Intent in=new Intent(PaintsTypes.this,PaintOne.class);
                startActivity(in);
            }
        });
    }
}
