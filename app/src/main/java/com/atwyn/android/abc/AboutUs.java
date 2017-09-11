package com.atwyn.android.abc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {
ImageView back1;
int click=0;
    TextView tx1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

WebView view = (WebView) findViewById(R.id.textContent);
String text;
text = "<html><body><p align=\"justify\">";
text+= "This is the text will be justified when displayed!!!";
text+= "</p></body></html>";
view.loadData(text, "text/html", "utf-8");
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
       //tx1=(TextView)findViewById(R.id.tx9) ;
        /*WebView view = (WebView) findViewById(R.id.textView5);
        String text;
        text = "<html><body><p align=\"justify\" style=\"border:#ff0000; background-color:#000000; font-family:sans-serif; color:#ffffff\" >";
        text+= "Giving maximum importance to customer satisfaction is the motto of ABC group. The pillars of the business stand on the credibility and lasting relationship gained from customer's happiness. We are always focused to achieve this trustworthiness from our clients. ABC Group believes in taking a customer centric approach with a promise to deliver quality service. Always sensitive towards the needs of our customers, we constantly strive to nurture good relationships with them. Our service pattern is very transparent which maintain strong bonds with our customers. We also provide excellent after sales service which brings peace of mind to the customers. It is our firm belief that a happy customer is a loyal customer. Hence we provide only the best to them in the form of excellent products and efficient services. By becoming a customer of ABC you can experience the depth of our value added service and know more about how you can benefit from us.";
        text+= "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");*/
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
                        Intent in = new Intent(AboutUs.this, Main2Activity.class);
                        // startActivity(in);
                        finish();
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }
            });
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ho);
            toolbar.setOverflowIcon(drawable);

            // Inflate a menu to be displayed in the toolbar
           //actionbar.inflateMenu(R.menu.actions);
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.



        //getMenuInflater().inflate(R.menu.mainproducts, menu);


        getMenuInflater().inflate(R.menu.home, menu);


        return true;


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==R.id.h1)
        {
            Intent inn = new Intent(AboutUs.this, Main2Activity.class);
            //inn.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
               /* inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);*/
            startActivity(inn);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        click = click + 1;
        if (click == 1) {
            click = 0;
            //finishAffinity();
            Intent in = new Intent(AboutUs.this, Main2Activity.class);
            // startActivity(in);
            finish();
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
        }

    }

}
