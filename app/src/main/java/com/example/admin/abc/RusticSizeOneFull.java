package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RusticSizeOneFull extends AppCompatActivity {
ImageView b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rustic_size_one_full);
        b5=(ImageView)findViewById(R.id.back);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RusticSizeOneFull.this,RusticSizeOne.class);
                startActivity(in);
            }
        });
    }
}
