package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PaintsOneFull extends AppCompatActivity {
ImageView bp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paints_one_full);

        bp3=(ImageView)findViewById(R.id.back31);
        bp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(PaintsOneFull.this,PaintOne.class);
                startActivity(in);
            }
        });
    }
}
