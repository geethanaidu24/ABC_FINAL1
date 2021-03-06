package com.atwyn.android.abc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

public class NewsDescription extends AppCompatActivity {

    ImageView back;
int click=0;
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

       //  Get intent dataintent.putExtra("NEWSIMAGE_KEY",details[0]);
        Intent i = this.getIntent(); // get Intent which we set from Previous Activity
        final String titleNews = i.getExtras().getString("NEWSTITLE");
        final String image = i.getExtras().getString("NEWSIMAGE_KEY");
        final String descriptn = i.getExtras().getString("NEWSDES_KEY");
        titleTxt.setText(titleNews);
        descriptionTxt.setText(descriptn);
        Glide.with(this)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //use this to cache
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .centerCrop()
                .crossFade()
                .into(selectedImage);
        //PicassoClient.downloadImage(c, image, selectedImage);
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
                        Intent in = new Intent(NewsDescription.this, News.class);
                        finish();
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }
            });
        }
    }
    public void onBackPressed() {
        //finishAffinity();
        click = click + 1;
        if (click == 1) {
            click = 0;
            Intent in = new Intent(NewsDescription.this, News.class);
            finish();
            /*in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);*/
        }
    }
}
