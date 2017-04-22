package com.example.admin.abc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Geetha on 4/20/2017 for accessing url to displaying images in gridview.
 */

public class ProductTypeSubTypeImages extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = "http://192.168.0.2/abc/getProductTypeSubTypeImages.php?ProductSubTypeId=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_subtype_images);

        final GridView gv = (GridView) findViewById(R.id.gv);
     gv.setNumColumns(2);
        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

        final int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
        final int pstid = intent.getExtras().getInt("PRODUCTSUBTYPEID_KEY");

        String urlAddress = url + pstid;

        new ProductTypeSubTypeImagesDownloader(ProductTypeSubTypeImages.this,urlAddress,gv).execute();

        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(ProductTypeSubTypeImages.this,ProductTypeSubTypes.class);
                    in.putExtra("PRODUCTTYPEID_KEY", ptid);
                    in.putExtra("PRODUCTSUBTYPEID_KEY", pstid);

                    startActivity(in);
                }
            });

            // Inflate a menu to be displayed in the toolbar
            actionbar.inflateMenu(R.menu.actions);
        }

    /*    back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ProductTypeSubTypeImages.this,ProductTypeSubTypes.class);
                in.putExtra("PRODUCTTYPEID_KEY", ptid);
                in.putExtra("PRODUCTSUBTYPEID_KEY", pstid);
                startActivity(in);
            }
        });*/
    }
}
