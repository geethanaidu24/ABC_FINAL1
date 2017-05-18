package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDescription extends AppCompatActivity {

    ImageView back;

    ImageView selectedImage;
    TextView titleTxt, descriptionTxt;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_description);
        selectedImage = (ImageView) findViewById(R.id.newsImage); //init a ImageView
        titleTxt = (TextView) findViewById(R.id.newstxt);
        descriptionTxt = (TextView) findViewById(R.id.description);

        // Get intent dataintent.putExtra("NEWSIMAGE_KEY",details[0]);
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        final String titleNews = i.getExtras().getString("NEWSTITLE");
        final String image = i.getExtras().getString("NEWSIMAGE_KEY");
        final String descriptn = i.getExtras().getString("NEWSDES_KEY");
        titleTxt.setText(titleNews);
        descriptionTxt.setText(descriptn);

        PicassoClient.downloadImage(c, image, selectedImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(NewsDescription.this, News.class);
                    finish();
                }
            });
        }
    }
}
