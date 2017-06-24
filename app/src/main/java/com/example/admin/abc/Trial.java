package com.example.admin.abc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Trial extends AppCompatActivity {
Button b1,b2;
    int click=0;
    private int selProductId;
    private String selProductName;
    ArrayList<MySQLDataBase> mySQLProSizeDataBases;
    ArrayList<MySQLDataBase> mySQLProTypeDataBases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
        selProductId = intent.getExtras().getInt("PRODUCTID_KEY");
        selProductName = intent.getExtras().getString("PRODUCTNAME_KEY");
        mySQLProTypeDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductTypeList");

        mySQLProSizeDataBases = (ArrayList<MySQLDataBase>) intent.getSerializableExtra("ProductSizeList");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;
                        Intent in = new Intent(Trial.this, Main2Activity.class);
                        //in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();
                        //finish();
                        // in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                }
            });




        }
            b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(Trial.this, AddProductsTypes.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.putExtra("PRODUCTID_KEY", selProductId);
                    in.putExtra("PRODUCTNAME_KEY", selProductName);
                    in.putExtra("ProductTypeList",mySQLProTypeDataBases);
                    startActivity(in);
                    //b2.setEnabled(false);
                    b2.setAlpha(.5f);
                    b2.setClickable(false);
                    b1.setEnabled(true);
                }
            }
        });
    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent in = new Intent(Trial.this, AddProductSizes.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("PRODUCTID_KEY", selProductId);
                in.putExtra("PRODUCTNAME_KEY", selProductName);
                in.putExtra("ProductSizeList",mySQLProSizeDataBases);
                startActivity(in);
                b2.setEnabled(true);
                //b1.setEnabled(true);
                b1.setAlpha(.5f);
                b1.setClickable(false);
            }
        }
    });
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(Trial.this, Main2Activity.class);
            //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
            finish();
        }



    }

}
