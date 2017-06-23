package com.example.admin.abc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Trial1 extends AppCompatActivity {
    Button b1,b2,b3;
    int click=0;
    private int selectedProducttypeid;
    private String selectedProducttype;
    private static int selectedProductId;
    private static String selectedProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial1);
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity
        selectedProductName = intent.getExtras().getString("PRODUCTNAME_KEY");
        selectedProductId = intent.getExtras().getInt("PRODUCTID_KEY");
        selectedProducttype = intent.getExtras().getString("PRODUCTTYPE_KEY");
        selectedProducttypeid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                        Intent in = new Intent(Trial1.this, Main2Activity.class);
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
        b1=(Button)findViewById(R.id.bb1);
        b2=(Button)findViewById(R.id.bb2);
        b3=(Button)findViewById(R.id.bb3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = click + 1;
                if (click == 1) {
                    click = 0;
                    Intent in = new Intent(Trial1.this, AddProductsSubType.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.putExtra("PRODUCTID_KEY", selectedProductId);
                    in.putExtra("PRODUCTNAME_KEY", selectedProductName);
                    in.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
                    in.putExtra("PRODUCTTYPE_KEY", selectedProducttype);
                    startActivity(in);
                    //b2.setEnabled(false);
                    b1.setEnabled(true);
                    // b3.setEnabled(false);
                    b2.setAlpha(.5f);
                    b2.setClickable(false);
                    b3.setAlpha(.5f);
                    b3.setClickable(false);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(Trial1.this, AddGridProductTypes.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.putExtra("PRODUCTID_KEY", selectedProductId);
                    in.putExtra("PRODUCTNAME_KEY", selectedProductName);
                    in.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
                    in.putExtra("PRODUCTTYPE_KEY", selectedProducttype);
                    startActivity(in);
                    b2.setEnabled(true);
                    // b1.setEnabled(false);
                    //b3.setEnabled(false);
                    b1.setAlpha(.5f);
                    b1.setClickable(false);
                    b3.setAlpha(.5f);
                    b3.setClickable(false);
                }
            }
        });
    b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent in = new Intent(Trial1.this, AddProductTypeSizes.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("PRODUCTID_KEY", selectedProductId);
                in.putExtra("PRODUCTNAME_KEY", selectedProductName);
                in.putExtra("PRODUCTTYPEID_KEY", selectedProducttypeid);
                in.putExtra("PRODUCTTYPE_KEY", selectedProducttype);
                startActivity(in);
                // b2.setEnabled(false);
                //  b1.setEnabled(false);
                b3.setEnabled(true);
                b2.setAlpha(.5f);
                b2.setClickable(false);
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
            Intent in = new Intent(Trial1.this, Main2Activity.class);
            //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
            finish();
        }
    }
}
