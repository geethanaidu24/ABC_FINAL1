package com.example.admin.abc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Geetha on 4/14/2017 for opening Product Types Size activity based on user clicked product .
 */

public class ProductTypeSizes extends AppCompatActivity {
    ImageView back;

    //Context c;
    final static String url = Config.productTypeSizesUrlAddress;

        @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_types_sizes);

            LinearLayout ll1= (LinearLayout) findViewById(R.id.productTypes_size);
           // LinearLayout pstll =(LinearLayout)findViewById(R.id.productTypes_size);
        final ListView lv = (ListView) findViewById(R.id.productTypeSizesLv);

        // Get intent data
        Intent intent = this.getIntent(); // get Intent which we set from Previous Activity

       final int pid = intent.getExtras().getInt("PRODUCTID_KEY");
       final String pname = intent.getExtras().getString("PRODUCTNAME_KEY");
       final int ptid = intent.getExtras().getInt("PRODUCTTYPEID_KEY");
           // getIntent().getSerializableExtra("LAYOUT_KEY");
      // final LinearLayout pstll = (LinearLayout) intent.getExtras().get("layout");
         //   final LinearLayout psll = (LinearLayout) intent.getExtras().get("layout");
        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendQueryParameter(Config.PRODUCTID_PARAM, Integer.toString(pid))
                .appendQueryParameter(Config.PRODUCTTYPEID_PARAM, Integer.toString(ptid))
                .build();
        URL urlAddress = null;
        try {
            urlAddress = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new ProductTypeSizesDownloader(ProductTypeSizes.this,urlAddress,lv,ll1,pid,ptid,pname).execute();
            Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);
            if (null != actionbar) {
                actionbar.setNavigationIcon(R.mipmap.backbutton);

                //  actionbar.setTitle(R.string.title_activity_settings);
                actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(ProductTypeSizes.this,ProductTypes.class);
                        in.putExtra("PRODUCTID_KEY", pid);
                        in.putExtra("PRODUCTNAME_KEY",pname);
                        startActivity(in);
                    }
                });

                actionbar.inflateMenu(R.menu.sizes);


                actionbar.setOnMenuItemClickListener(
                        new Toolbar.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                // Handle menu item click event

                                int id = item.getItemId();

                                if (id == R.id.gridadd) {
                                    Intent in = new Intent(ProductTypeSizes.this, AddProductTypeSizes.class);
                                    in.putExtra("PRODUCTID_KEY", pid);
                                    in.putExtra("PRODUCTNAME_KEY",pname);
                                    in.putExtra("PRODUCTTYPEID_KEY",ptid);
                                    startActivity(in);
                                }
                                if (id == R.id.griddelete) {
                                    Intent in = new Intent(ProductTypeSizes.this, DeleteProductTypeSizes.class);
                                    in.putExtra("PRODUCTID_KEY", pid);
                                    in.putExtra("PRODUCTNAME_KEY",pname);
                                    in.putExtra("PRODUCTTYPEID_KEY",ptid);
                                    startActivity(in);
                                }
                                return true;
                            }
                        });
            }

    }

}
