package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ShowerroomSize extends AppCompatActivity {
    ImageView b12;
    Button b13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showerroom_size);
        b12=(ImageView)findViewById(R.id.back);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(ShowerroomSize.this,Bathroom.class);
                startActivity(in);
            }
        });
        b13=(Button)findViewById(R.id.buttonws);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(ShowerroomSize.this,Basins.class);
                startActivity(in);
            }
        });

    }

}
