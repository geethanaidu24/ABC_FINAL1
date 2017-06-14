package com.example.admin.abc;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class AboutUs extends AppCompatActivity {
ImageView back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/

       /* back1 = (ImageView) findViewById(R.id.back);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AboutUs.this, Main2Activity.class);
                startActivity(in);
            }
        });*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent in=new Intent(AboutUs.this,Main2Activity.class);
                   // startActivity(in);
                    finish();
                }
            });

            // Inflate a menu to be displayed in the toolbar
           //actionbar.inflateMenu(R.menu.actions);
        }

    }
    public void onBackPressed() {
        //finishAffinity();
        Intent in=new Intent(AboutUs.this,Main2Activity.class);
        // startActivity(in);
        finish();

    }

}
